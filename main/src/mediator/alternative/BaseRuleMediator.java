package mediator.alternative;

import mediator.Mediator;
import player.Player;
import role.*;
import ui.GameDisplay;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * Mediator that runs a basic game.
 */
public class BaseRuleMediator implements Mediator {
    private final List<Villager> villagers;
    private final List<WereWolf> wereWolves;
    private final List<Role> victims;
    private Seer seer;
    private Witch witch;
    private boolean gameOver;
    private final GameDisplay gameDisplay;
    private WeatherMediator weatherMediator;

    /**
     * These are the essential roles to attribute in a minimal basic game.
     */
    private final static List<BiFunction<Player, Mediator, Role>> primaryRoles = List.of(
            WereWolf::new, WereWolf::new, Seer::new, Villager::new,
            Villager::new, Villager::new, Witch::new, Villager::new
    );

    /**
     * These are other roles to attribute for a basic game
     */
    private final static List<BiFunction<Player,Mediator, Role>> otherRoles = List.of(
            WereWolf::new, Villager::new, Villager::new, Villager::new  // ...
    );

    /**
     * Constructor for the BaseRuleMediator
     * @param players the list of players for the game
     * @param display the instance that will handle the display of the game.
     */
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

    /**
     * Setter for a WhetherMediator
     * @param mediator the instance of WeatherMediator to set
     */
    void setWeatherMediator(WeatherMediator mediator) {
        this.weatherMediator = mediator;
    }

    /**
     * Plays the seer turn.
     */
    private void seerTurn() {
        if(this.seer != null && this.seer.isAlive()) {
            this.gameDisplay.showSeerTurn(this.seer);
            this.seer.activate();
        }
    }

    /**
     * Plays the witch turn.
     */
    private void witchTurn() {
        if(this.witch != null && this.witch.isAlive()) {
            this.gameDisplay.showWitchTurn(this.witch);
            this.witch.activate();
        }
    }

    /**
     * Plays the were wolves turn.
     */
    private void wereWolvesTurn() {
        this.gameDisplay.showWolvesTurn();
        List<Role> targetsAlive = new ArrayList<>(this.getRolesAlive().toList());
        this.weatherMediator.wereWolvesTurn(this.getWereWolvesAlive().toList(), targetsAlive);
    }

    /**
     * PLays the villagers turn.
     */
    private void villagersTurn(){
        this.weatherMediator.villagersTurn(this.getRolesAlive().toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Role> getRolesAlive() {
        return Stream.concat(getWereWolvesAlive(), getNiceGuysAlive());
    }

    /**
     * Gets the all living nice guys
     * @return stream with all living role in the game
     */
    public Stream<Role> getNiceGuysAlive(){
        return Stream.concat(villagers.stream(), Stream.of(this.witch,this.seer).filter(Objects::nonNull)).filter(Role::isAlive);
    }

    /**
     * Gets the remaining living were wolves.
     * @return Stream of the remaining were wolves
     */
    public Stream<WereWolf> getWereWolvesAlive() {
        return wereWolves.stream().filter(WereWolf::isAlive);
    }

    /**
     * This function is used to create a vote, for example when the werewolves or the village have to vote to eliminate a villager.
     * @param voters the roles that will participate in the vote
     * @param chooseAmong among which roles to choose
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(){
        while (!isGameOver()) this.playTurn();

    }

    /**
     * Plays a turn.
     */
    private void playTurn() {
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMinPlayers() {
        return 8;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxPlayers() {
        return 18;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Role> getVictims() {
        return this.victims;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean askHeal() {
        return this.gameDisplay.askHeal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean askKill() {
        return this.gameDisplay.askKill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role selectRole(List<Role> roles, String reason) {
        return this.gameDisplay.selectRole(roles, reason);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayVictims() {
        this.gameDisplay.showVictims(this.victims);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void kill(Role role) {
        this.victims.add(role);
        role.kill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void heal(Role role) {
        this.victims.removeIf((r) -> r.getId() == role.getId());
        role.heal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayCurrentPlayer(Role role) {
        this.gameDisplay.showPlayerName(role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameDisplay getGameDisplay() {
        return this.gameDisplay;
    }
}