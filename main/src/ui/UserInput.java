package ui;

import role.Role;
import java.util.List;

public interface UserInput {
    boolean askHeal();
    boolean askKill();
    Role selectRole(List<Role> roles, String reason);
}