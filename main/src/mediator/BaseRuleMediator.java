package mediator;

import player.Player;
import role.*;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;


// pour des parties de type "III distribution des cartes pour parties simplifiées" : https://www.regledujeu.fr/loup-garou-regle/

public class BaseRuleMediator implements Mediator{
    private final List<Villager> villagers;
    private final List<WereWolf> wereWolves;
    private Seer seer;
    private Witch witch;
    private boolean gameOver;

    // les rôles essentiels à attribuer dans une partie de 8 joueurs
    private final static List<BiFunction<Player, Mediator, Role>> primaryRoles = List.of(
            WereWolf::new, WereWolf::new, Seer::new, Villager::new,
            Villager::new, Villager::new, Villager::new, Villager::new
    );

    // rôles à attribuer en plus si assez de joueurs
    private final static List<BiFunction<Player,Mediator, Role>> otherRoles = List.of(
            Villager::new, Villager::new, Villager::new, WereWolf::new  // ...
    );


    public BaseRuleMediator(List<Player> players) {
        this.villagers = new ArrayList<>();
        this.wereWolves = new ArrayList<>();
        if (!(getMinPlayers() <= players.size() && players.size() <= getMaxPlayers())) {
            throw new IllegalArgumentException("Not enough player or too many players.");
        }
        this.assignRoles(players);
        this.gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void displayRole(Role role) {
        //this.ui.displaytruc
    }

    /**
     * Vérifier les conditions de victoire de la partie. La partie s'arrête soit si les loups garous sont tous morts,
     * soit si il n'y a plus assez de villageois pour les battre lors d'un vote de vilalge
     */
    private void computeWinConditions(){
        if(this.getNiceGuysAlive().noneMatch(Role::isAlive) && this.wereWolves.stream().noneMatch(WereWolf::isAlive))
            this.gameOver = true;
    }

    public Stream<Role> getRolesAlive() {
        return Stream.concat(getWereWolvesAlive(), getNiceGuysAlive());
    }

    public Stream<Role> getNiceGuysAlive(){
        return Stream.concat(villagers.stream(), Stream.of(this.witch,this.seer).filter(Objects::nonNull)).filter(Role::isAlive);
    }

    public Stream<WereWolf> getWereWolvesAlive() {
        return wereWolves.stream().filter(WereWolf::isAlive);
    }

    /**
     * Cette fonction existe pour créer un vote, par exemple quand les loups garous ou le village doivent voter pour l'élimination d'un villageois,
     * @param voters le groupe de votants, ils doivent tous être vivants
     * @param chooseAmong parmi quel sous-ensemble on propose aux votants de voter
     * @return le joueur sélectionné
     */
    private void killVote(List<? extends Role> voters, List<Role> chooseAmong) {
        while (true) {
            Map<Role, Integer> voteMap = new HashMap<>();
            for (Role voter : voters) {
                Role choice = voter.vote(chooseAmong);
                voteMap.merge(choice, 1, Integer::sum);
            }
            Optional<Map.Entry<Role, Integer>> chosenRole = voteMap.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue());

            if (voteMap.values().stream().filter((v) -> v == chosenRole.get().getValue()).count() > 1) {
                continue;
            }
            chosenRole.ifPresent(r -> r.getKey().kill());
            return;
        }

    }

    @Override
    public void playTurn() {
        if (isGameOver()){
            return;
        }
        // Le village s'endort
        // La voyante se reveille
        if(this.seer != null)
            this.seer.activate();

        // demander aux loups-garou de voter pour éliminer un joueur
        this.killVote(getWereWolvesAlive().toList(), getRolesAlive().toList());

        // Tour de la sorciere
        if(this.witch != null)
            this.witch.activate();

        // Le village se reveille

        this.computeWinConditions();
        if (gameOver) return;

        List<Role> allRoles = this.getRolesAlive().toList();
        this.killVote(allRoles,allRoles);
        computeWinConditions();
    }

    public void assignRoles(List<Player> players) {
        Collections.shuffle(players);

        for (int i = 0; i < players.size(); ++i) {
            BiFunction<Player, Mediator, Role> constructor = i >= primaryRoles.size() ?
                    otherRoles.get(i % otherRoles.size()) :
                    primaryRoles.get(i);
            Role role = constructor.apply(players.get(i), this);

            switch (role) {
                case WereWolf w -> wereWolves.add(w);
                case Villager v -> villagers.add(v);
                case Seer s -> seer = s;
                case Witch w -> witch = w;
                default -> throw new IllegalStateException("Unexpected role: " + role);
            }
        }
    }

    @Override
    public int getMinPlayers() {
        return 8;
    }

    @Override
    public int getMaxPlayers() {
        return 18;
    }

}