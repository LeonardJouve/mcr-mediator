package mediator.alternative;

import mediator.Mediator;
import player.Player;
import role.*;
import ui.GameDisplay;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class BaseRuleMediator implements Mediator {
    private final List<Villager> villagers;
    private final List<WereWolf> wereWolves;
    private final List<Role> victims;
    private Seer seer;
    private Witch witch;
    private boolean gameOver;
    private final GameDisplay gameDisplay;
    private WeatherMediator weatherMediator;

    // les rôles essentiels à attribuer dans une partie de 8 joueurs
    private final static List<BiFunction<Player, Mediator, Role>> primaryRoles = List.of(
            WereWolf::new, WereWolf::new, Seer::new, Villager::new,
            Villager::new, Villager::new, Witch::new, Villager::new
    );

    // rôles à attribuer en plus si assez de joueurs
    private final static List<BiFunction<Player,Mediator, Role>> otherRoles = List.of(
            WereWolf::new, Villager::new, Villager::new, Villager::new  // ...
    );

    public BaseRuleMediator(List<Player> players, GameDisplay display) {
        this.gameDisplay = display;
        this.villagers = new ArrayList<>();
        this.wereWolves = new ArrayList<>();
        this.victims = new ArrayList<>();
        if (players.size() < getMinPlayers() || players.size() > getMaxPlayers()) {
            throw new IllegalArgumentException("Not enough player or too many players.");
        }
        this.assignRoles(players);
        this.gameOver = false;
        this.weatherMediator = new NormalWeatherMediator(this);
    }

    void setWeatherMediator(WeatherMediator mediator) {
        this.weatherMediator = mediator;
    }

    private void seerTurn() {
        if(this.seer != null && this.seer.isAlive()) {
            this.gameDisplay.showSeerTurn(this.seer);
            this.seer.activate();
        }
    }

    private void witchTurn() {
        if(this.witch != null && this.witch.isAlive()) {
            this.gameDisplay.showWitchTurn(this.witch);
            this.witch.activate();
        }
    }

    private void wereWolvesTurn() {
        this.gameDisplay.showWolvesTurn();
        this.weatherMediator.wereWolvesTurn(this.getWereWolvesAlive().toList(), this.getRolesAlive().toList());
    }

    private void villagersTurn(){
        this.weatherMediator.villagersTurn(this.getRolesAlive().toList());
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void displayRole(Role role) {
        this.gameDisplay.showRoleReveal(role);
    }

    /**
     * Vérifier les conditions de victoire de la partie. La partie s'arrête soit si les loups garous sont tous morts,
     * soit si il n'y a plus assez de villageois pour les battre lors d'un vote de vilalge
     */
    private void computeWinConditions() {
        long aliveVillagers = this.getNiceGuysAlive().count();
        long aliveWerewolves = this.getWereWolvesAlive().count();

        if (aliveWerewolves == 0) {
            this.gameDisplay.showVillagersWin();
            this.gameOver = true;
        } else if (aliveWerewolves >= aliveVillagers) {
            this.gameDisplay.showWerewolvesWin();
            this.gameOver = true;
        }
    }
    @Override
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
     */
    Role killVote(List<? extends Role> voters, List<Role> chooseAmong) {
        while (true) {
            Map<Role, Integer> voteMap = new HashMap<>();
            for (Role voter : voters) {
                Role choice = voter.vote(chooseAmong);
                voteMap.merge(choice, 1, Integer::sum);
            }
            Optional<Map.Entry<Role, Integer>> chosenRole = voteMap.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue());

            if (voteMap.values().stream().filter((v) -> Objects.equals(v, chosenRole.get().getValue())).count() > 1) {
                this.gameDisplay.showVoteTie();
                continue;
            }

            if (chosenRole.isEmpty())
                throw new RuntimeException("No chosen role found");

            Role role = chosenRole.get().getKey();

            this.victims.add(role);
            role.kill();
            this.gameDisplay.showVoteResults(voteMap, role);

            return role;
        }

    }

    @Override
    public void start(){
        this.playTurn();
    }

    private void playTurn() {
        if (isGameOver()){
            return;
        }
        this.weatherMediator.trigger();
        this.victims.clear();
        this.gameDisplay.showNightStart();
        this.seerTurn();
        this.wereWolvesTurn();
        this.witchTurn();
        this.gameDisplay.showDayStart(this.victims);

        this.computeWinConditions();
        if (gameOver) return;

        this.villagersTurn();

        this.computeWinConditions();
        this.playTurn();
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

    @Override
    public List<Role> getVictims() {
        return this.victims;
    }

    @Override
    public boolean askHeal() {
        return this.gameDisplay.askHeal();
    }

    @Override
    public boolean askKill() {
        return this.gameDisplay.askKill();
    }

    @Override
    public Role selectRole(List<Role> roles, String reason) {
        return this.gameDisplay.selectRole(roles, reason);
    }

    @Override
    public void displayVictims() {
        this.gameDisplay.showVictims(this.victims);
    }

    @Override
    public void kill(Role role) {
        this.victims.add(role);
        role.kill();
    }

    @Override
    public void heal(Role role) {
        this.victims.removeIf((r) -> r.getId() == role.getId());
        role.heal();
    }

    @Override
    public void displayCurrentPlayer(Role role) {
        this.gameDisplay.showPlayerName(role);
    }

    @Override
    public GameDisplay getGameDisplay() {
        return this.gameDisplay;
    }
}