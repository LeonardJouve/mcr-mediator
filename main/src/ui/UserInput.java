package ui;

import role.Role;
import java.util.List;

public interface UserInput {
    /**
     * Asks whether to heal or not.
     * @return true if the heal should happen.
     */
    boolean askHeal();

    /**
     * Asks whether to kill or not.
     * @return true if the kill should happen.
     */
    boolean askKill();

    /**
     * Asks to choose a role among a list of roles.
     * @param roles the list of roles among which the role should be chosen.
     * @param reason the reason for which the role should be chosen.
     * @return the chosen role.
     */
    Role selectRole(List<Role> roles, String reason);
}