package ui.graphical;

import role.Role;
import role.Seer;
import role.Witch;
import ui.GameDisplay;
import ui.UserInput;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class GraphicalDisplay implements GameDisplay {
    private final JTextArea logArea;
    private final UserInput userInput;

    public GraphicalDisplay(UserInput userInput) {
        this.userInput = userInput;
        this.logArea = new JTextArea(15, 40);
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
    }

    public void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    @Override
    public void showRoleReveal(Role revealedRole) {
        log("[Révélation] " + revealedRole.getPlayer().getName() + " est un " + revealedRole.getRoleName());
    }

    @Override
    public void showNightStart() {
        log("\n=== NUIT ===");
    }

    @Override
    public void showDayStart(List<Role> victims) {
        log("\n=== JOUR ===");
        if (!victims.isEmpty()) {
            log("Victimes cette nuit:");
            victims.forEach(v -> log("  - " + v.getPlayer().getName()));
        } else {
            log("Aucune victime cette nuit !");
        }
    }

    @Override
    public void showSeerTurn(Seer seer) {
        log("La voyante (" + seer.getPlayer().getName() + ") se réveille...");
    }

    @Override
    public void showWolvesTurn() {
        log("Les loups-garous se réveillent...");
    }

    @Override
    public void showWitchTurn(Witch witch) {
        log("La sorcière (" + witch.getPlayer().getName() + ") se réveille...");
    }

    @Override
    public void showVoteResults(Map<Role, Integer> votes, Role eliminated) {
        log("[Résultats du vote]");
        votes.forEach((role, count) ->
                log("  " + role.getPlayer().getName() + ": " + count + " votes")
        );
        if (eliminated != null) {
            log(eliminated.getPlayer().getName() + " a été éliminé !");
        }
    }

    @Override
    public void showGameOver(String winner) {
        log("\n=== FIN DE PARTIE ===");
        log("Les " + winner + " ont gagné !");
    }

    @Override
    public void showVictims(List<Role> roles) {
        log("Victimes:");
        roles.forEach(role -> log("  - " + role.getPlayer().getName()));
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
        return this.userInput.askHeal();
    }

    @Override
    public boolean askKill() {
        return this.userInput.askKill();
    }

    @Override
    public Role selectRole(List<Role> roles, String reason) {
        return this.userInput.selectRole(roles, reason);
    }

    @Override
    public void showPlayerName(Role role) {
        log("Joueur : " + role.getPlayer().getName());
    }

    @Override
    public void showVoteTie() {
        log("Egalité des résultats du vote.");
    }

    @Override
    public void showMessage(String message) {
        log(message);
    }

    public JComponent getLogComponent() {
        return new JScrollPane(logArea);
    }
}