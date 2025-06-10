package ui;

import role.Role;

import java.util.List;
import java.util.Map;

public interface GameDisplay {
    void showRoleReveal(Role revealedRole);
    void showNightStart();
    void showDayStart(List<Role> victims);
    void showSeerTurn();
    void showWolvesTurn();
    void showWitchTurn();
    void showVoteResults(Map<Role, Integer> votes, Role eliminated);
    void showGameOver(String winner);
    void showVictims(List<Role> roles);
    void showVillagersWin();
    void showWerewolvesWin();
    boolean askHeal();
    boolean askKill();
    Role selectRole(List<Role> roles);
    void showPlayerName(Role role);
}