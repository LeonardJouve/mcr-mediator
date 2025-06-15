package ui;

import role.Role;
import role.Seer;
import role.Witch;

import java.util.List;
import java.util.Map;

/**
 * Interface for rendering game events and receiving user actions.
 */
public interface GameDisplay extends UserInput {
    /**
     * Display the reveal of a role to the players.
     *
     * @param revealedRole the Role object being revealed
     */
    void showRoleReveal(Role revealedRole);
    /**
     * Display the start of the night phase.
     */
    void showNightStart();

    /**
     * Display the start of the day phase and report victims from the night.
     *
     * @param victims list of Roles eliminated during the night
     */
    void showDayStart(List<Role> victims);

    /**
     * Display the seer's turn prompt.
     *
     * @param seer the Seer role taking its action
     */
    void showSeerTurn(Seer seer);

    /**
     * Display the werewolves' turn prompt.
     */
    void showWolvesTurn();

    /**
     * Display the witch's turn prompt.
     *
     * @param witch the Witch role taking its action
     */
    void showWitchTurn(Witch witch);

    /**
     * Display the results of the voting phase.
     *
     * @param votes map of each Role to the number of votes received
     * @param eliminated the Role that was eliminated (null if tie or none)
     */
    void showVoteResults(Map<Role, Integer> votes, Role eliminated);

    /**
     * Display the end of game summary and announce the winner.
     *
     * @param winner name of the winning side (e.g., "werewolves", "villagers")
     */
    void showGameOver(String winner);

    /**
     * Show a list of roles that were victims in the current phase.
     *
     * @param roles list of Roles eliminated
     */
    void showVictims(List<Role> roles);

    /**
     * Display that the villagers have won the game.
     */
    void showVillagersWin();

    /**
     * Display that the werewolves have won the game.
     */
    void showWerewolvesWin();

    /**
     * Display the name of the player for a given role.
     *
     * @param role the Role whose player name will be shown
     */
    void showPlayerName(Role role);

    /**
     * Display that the vote resulted in a tie.
     */
    void showVoteTie();

    /**
     * Display a generic message to the user.
     *
     * @param message the text message to display
     */
    void showMessage(String message);
}