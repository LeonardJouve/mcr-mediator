package mediator;

import player.Player;
import role.*;

import java.util.*;
import java.util.function.BiFunction;

public abstract class Mediator {
    protected final MediatorState mediatorState;
    protected final List<Role> villagers;
    protected final List<Role> wereWolves;

    protected Mediator(MediatorState mediatorState) {
        this.mediatorState = mediatorState;
        this.villagers = new ArrayList<>();
        this.wereWolves = new ArrayList<>();
    }

    public void playTurn() {
        // TODO

        // Les loups garous choisissent et tuent un villageois
        this.killVote(this.wereWolves, this.villagers);
        // mediatorState.setTargets();

        // Activer tous les roles
        List<Role> allRoles = new ArrayList<>();
        allRoles.addAll(this.villagers);
        allRoles.addAll(this.wereWolves);
        Collections.shuffle(allRoles);
        for (Role role : allRoles) {
            if (role.isAlive()) role.activate();
        }

        // Le village choisit une personne à éliminer
        this.killVote(allRoles, allRoles);
    }

    public abstract int getMinPlayers();

    public abstract int getMaxPlayers();

    // pour commencer la partie. Retourne false si pas assez de joueurs
    public boolean start() {
        if (mediatorState.getPlayers().size() < getMinPlayers() || mediatorState.getPlayers().size() > getMaxPlayers()) {
            return false;
        }
        assignRoles();
        System.out.println("Assigned roles !");
        return true;
    }

    public boolean isGameOver() {
        return villagers.isEmpty() || wereWolves.isEmpty();
    }

    protected abstract List<BiFunction<Player, MediatorState, Role>> getPrimaryRoles();

    protected abstract List<BiFunction<Player, MediatorState, Role>> getSecondaryRoles();

    private void assignRoles() {
        List<Player> players = mediatorState.getPlayers();
        Collections.shuffle(players);

        List<BiFunction<Player, MediatorState, Role>> primaryRoles = getPrimaryRoles();
        List<BiFunction<Player, MediatorState, Role>> secondaryRoles = getSecondaryRoles();

        for (int i = 0; i < players.size(); ++i) {
            BiFunction<Player, MediatorState, Role> constructor = i >= primaryRoles.size() ?
                    secondaryRoles.get(i % secondaryRoles.size()) :
                    primaryRoles.get(i);
            Role role = constructor.apply(players.get(i), mediatorState);

            if (role.isWereWolf()) {
                wereWolves.add(role);
            } else {
                villagers.add(role);
            }
        }
    }

    /**
     * Cette fonction existe pour créer un vote, par exemple quand les loups garous ou le village doivent voter pour l'élimination d'un villageois,
     * @param voters le groupe de votants, ils doivent tous être vivants
     * @param chooseAmong parmi quel sous-ensemble on propose aux votants de voter
     * @return le joueur sélectionné
     */
    private void killVote(List<Role> voters, List<Role> chooseAmong) {
        Map<Role, Integer> result = new HashMap<>(); // map qui sert à récolter les votes pour un joueur lors d'un "tour de vote"

        // demander aux votants de voter
        for (Role voter : voters) {
            Role choice = voter.chooseRole(chooseAmong);
            result.merge(choice, 1, Integer::sum);
        }

        // comptabiliser le nombre de voix. Si il y a une égalité, choisit le premier
        Optional<Role> chosenRole = result.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey);

        chosenRole.ifPresent(r -> r.setIsAlive(false));
    }
}
