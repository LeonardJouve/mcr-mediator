package ui.console;

import role.Role;
import ui.GameDisplay;
import ui.UserInput;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleDisplay implements GameDisplay {
    private final UserInput userInput;

    public ConsoleDisplay(UserInput userInput) {
        this.userInput = userInput;
    }

    @Override
    public void showRoleReveal(Role revealedRole) {
        System.out.println(revealedRole.getPlayer().getName() +
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
        victims.forEach(this::showRoleReveal);
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
    public void showVictims(List<Role> roles) {
        System.out.println("Les victimes sont: " + roles
                .stream()
                .map((r) -> r.getPlayer().getName())
                .collect(Collectors.joining(", ", "[", "]")));
    }

    @Override
    public void showVillagersWin() {
        showGameOver("villageois");
    }

    @Override
    public void showWerewolvesWin() {
        showGameOver("loups-garous");
    }

    @Override
    public boolean askHeal() {
        boolean shouldHeal = this.userInput.askHeal();
        if (shouldHeal) {
            System.out.println("La sorcière utilise une potion de soin");
        }

        return shouldHeal;
    }

    @Override
    public boolean askKill() {
        boolean shouldKill = this.userInput.askKill();
        if (shouldKill) {
            System.out.println("La sorcière utilise une potion empoisonnée");
        }

        return shouldKill;
    }

    @Override
    public Role selectRole(List<Role> roles) {
        return this.userInput.selectRole(roles);
    }

    @Override
    public void showPlayerName(Role role) {
        System.out.println("Joueur : " + role.getPlayer().getName());
    }
}
