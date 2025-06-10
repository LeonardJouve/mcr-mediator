package ui;

import role.Role;
import role.Seer;
import role.Witch;

import java.util.List;
import java.util.Map;

public interface GameDisplay extends UserInput {
    void showRoleReveal(Role revealedRole);
    void showNightStart();
    void showDayStart(List<Role> victims);
    void showSeerTurn(Seer seer);
    void showWolvesTurn();
    void showWitchTurn(Witch witch);
    void showVoteResults(Map<Role, Integer> votes, Role eliminated);
    void showGameOver(String winner);
    void showVictims(List<Role> roles);
    void showVillagersWin();
    void showWerewolvesWin();
    void showPlayerName(Role role);
    void showVoteTie();
    void showMessage(String message);
}