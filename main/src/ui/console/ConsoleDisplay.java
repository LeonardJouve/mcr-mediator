package ui.console;

import role.Role;
import role.Seer;
import role.Witch;
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
    public void showSeerTurn(Seer seer) {
        System.out.println("La voyante (" + seer.getPlayer().getName() + ") se réveille...");
    }

    @Override
    public void showWolvesTurn() {
        System.out.println("Les loup-garou se réveille...");
    }

    @Override
    public void showWitchTurn(Witch witch) {
        System.out.println("La sorcière (" + witch.getPlayer().getName() + ")  se réveille...");
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
    public Role selectRole(List<Role> roles, String reason) {
        return this.userInput.selectRole(roles, reason);
    }

    @Override
    public void showPlayerName(Role role) {
        System.out.println("Joueur : " + role.getPlayer().getName());
    }

    @Override
    public void showVoteTie() {
        System.out.println("Egalité des résultats du vote.");
    }
}
