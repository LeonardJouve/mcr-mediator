package ui.console;

import role.Role;
import role.Seer;
import role.Witch;
import ui.GameDisplay;
import ui.UserInput;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Console-based implementation of the GameDisplay interface.
 * <p>
 * This class handles rendering game events and messages to the standard output,
 * and delegates user input prompts to an injected UserInput implementation.
 */
public class ConsoleDisplay implements GameDisplay {
    private final UserInput userInput;

    /**
     * Constructor for ConsoleDisplay
     * @param userInput the UserInput implementation to solicit user actions
     */
    public ConsoleDisplay(UserInput userInput) {
        this.userInput = userInput;
    }

    /**
     * Display the reveal of a role to the console.
     *
     * @param revealedRole the Role being revealed
     */
    @Override
    public void showRoleReveal(Role revealedRole) {
        System.out.println(revealedRole.getPlayer().getName() +
                " est un " + revealedRole.getRoleName());
    }

    /**
     * Display the start of the night phase.
     */
    @Override
    public void showNightStart() {
        System.out.println("\n=== NUIT ===");
    }

    /**
     * Display the start of the day phase and list nighttime victims.
     *
     * @param victims list of Roles eliminated during night
     */
    @Override
    public void showDayStart(List<Role> victims) {
        System.out.println("\n=== JOUR ===");
        System.out.println("Le village se réveille sans:");
        victims.forEach(this::showRoleReveal);
    }

    /**
     * Prompt the seer to take their action.
     *
     * @param seer the Seer role taking its turn
     */
    @Override
    public void showSeerTurn(Seer seer) {
        System.out.println("La voyante (" + seer.getPlayer().getName() + ") se réveille...");
    }

    /**
     * Prompt the werewolves to take their action.
     */
    @Override
    public void showWolvesTurn() {
        System.out.println("Les loup-garou se réveille...");
    }

    /**
     * Prompt the witch to take their action.
     *
     * @param witch the Witch role taking its turn
     */
    @Override
    public void showWitchTurn(Witch witch) {
        System.out.println("La sorcière (" + witch.getPlayer().getName() + ")  se réveille...");
    }

    /**
     * Display the results of the voting phase.
     *
     * @param votes map of each Role to vote count
     * @param eliminated the Role eliminated, or null if no elimination
     */
    @Override
    public void showVoteResults(Map<Role, Integer> votes, Role eliminated) {
        System.out.println("Résultat du vote :");
        votes.forEach((role, count) ->
                System.out.println(role.getPlayer().getName() + ": " + count + " votes"));

        if (eliminated != null) {
            System.out.println(eliminated.getPlayer().getName() + " a été tué.");
        }
    }

    /**
     * Display the end-of-game summary and announce the winner.
     *
     * @param winner name of the winning side
     */
    @Override
    public void showGameOver(String winner) {
        System.out.println("\n=== FIN DE PARTIE ===");
        System.out.println("Les " + winner + " ont gagner.");
    }

    /**
     * Show list of roles eliminated in the current phase.
     *
     * @param roles list of Roles eliminated
     */
    @Override
    public void showVictims(List<Role> roles) {
        System.out.println("Les victimes sont: " + roles
                .stream()
                .map((r) -> r.getPlayer().getName())
                .collect(Collectors.joining(", ", "[", "]")));
    }

    /**
     * Display that the villagers have won the game.
     */
    @Override
    public void showVillagersWin() {
        showGameOver("villageois");
    }

    /**
     * Display that the werewolves have won the game.
     */
    @Override
    public void showWerewolvesWin() {
        showGameOver("loups-garous");
    }


    /**
     * Shows the ask heal message
     * @return whether to heal or not
     */
    @Override
    public boolean askHeal() {
        boolean shouldHeal = this.userInput.askHeal();
        if (shouldHeal) {
            System.out.println("La sorcière utilise une potion de soin");
        }

        return shouldHeal;
    }

    /**
     * Shows the ask kill message
     * @return whether to kill or not
     */
    @Override
    public boolean askKill() {
        boolean shouldKill = this.userInput.askKill();
        if (shouldKill) {
            System.out.println("La sorcière utilise une potion empoisonnée");
        }

        return shouldKill;
    }

    @Override
    public Role selectRole(List<Role> roles, String reason) {
        return this.userInput.selectRole(roles, reason);
    }

    /**
     * Display the name of the player for the given role.
     *
     * @param role the Role whose player name will be shown
     */
    @Override
    public void showPlayerName(Role role) {
        System.out.println("Joueur : " + role.getPlayer().getName());
    }

    /**
     * Display that the vote resulted in a tie.
     */
    @Override
    public void showVoteTie() {
        System.out.println("Egalité des résultats du vote.");
    }

    /**
     * Display a generic message to the console.
     *
     * @param message the text message to display
     */
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
