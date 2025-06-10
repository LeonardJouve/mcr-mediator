package ui.graphical;

import role.Role;
import ui.UserInput;

import javax.swing.*;
import java.util.List;

public class GraphicalInput implements UserInput {
    private final JFrame parentFrame;

    public GraphicalInput(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    @Override
    public boolean askHeal() {
        int response = JOptionPane.showConfirmDialog(parentFrame,
                "Voulez-vous utiliser votre potion de soin ?",
                "Tour de la sorcière",
                JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    @Override
    public boolean askKill() {
        int response = JOptionPane.showConfirmDialog(parentFrame,
                "Voulez-vous utiliser votre potion empoisonnée ?",
                "Tour de la sorcière",
                JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    @Override
    public Role selectRole(List<Role> roles) {
        Object[] options = roles.stream()
                .map(role -> role.getPlayer().getName())
                .toArray();

        String selection = (String) JOptionPane.showInputDialog(parentFrame,
                "Choisissez un joueur:",
                "Sélection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (selection == null) {
            return roles.get(0); // Retourne le premier si annulation
        }

        return roles.stream()
                .filter(role -> role.getPlayer().getName().equals(selection))
                .findFirst()
                .orElse(roles.get(0));
    }
}