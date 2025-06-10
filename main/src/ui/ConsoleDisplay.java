package ui;

import role.Role;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleDisplay implements GameDisplay{
    @Override
    public void showRoleReveal(Role revealedRole) {
        System.out.println("[VOYANTE] " + revealedRole.getPlayer().getName() +
                " est un " + revealedRole.getRoleName());
    }

    @Override
    public void showNightStart() {
        System.out.println("\n=== NUIT ===");
    }

    @Override
    public void showDayStart(List<Role> victims) {
        System.out.println("\n=== JOUR ===");
        System.out.println("Le village se réveille sans:");
        System.out.println(victims.stream().map((r) -> r.getPlayer().getName() + ": " + r.getRoleName()).collect(Collectors.joining(", ", "[", "]")));
    }

    @Override
    public void showSeerTurn() {
        System.out.println("La voyante se réveille...");
    }

    @Override
    public void showWolvesTurn() {
        System.out.println("Les loup-garou se réveille...");
    }

    @Override
    public void showWitchTurn() {
        System.out.println("La sorcière se réveille...");
    }

    @Override
    public void showVoteResults(Map<Role, Integer> votes, Role eliminated) {
        System.out.println("Résultat du vote :");
        votes.forEach((role, count) ->
                System.out.println(role.getPlayer().getName() + ": " + count + " votes"));

        if (eliminated != null) {
            System.out.println(eliminated.getPlayer().getName() + " a été tué.");
        }
    }

    @Override
    public void showGameOver(String winner) {
        System.out.println("\n=== FIN DE PARTIE ===");
        System.out.println("Les " + winner + " ont gagner.");
    }

    @Override
    public void showPlayerList(List<Role> roles) {
        System.out.println("=== JOUEURS ===");
        roles.forEach(role ->
                System.out.println("- " + role.getPlayer().getName() +
                        " (" + role.getRoleName() + ")"));
    }

    @Override
    public void showVillagersWin() {
        showGameOver("villageois");
    }

    @Override
    public void showWerewolvesWin() {
        showGameOver("loups-garous");
    }
}
