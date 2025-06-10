package mediator.alternative;

import role.Role;
import role.WereWolf;

import java.util.List;

public interface WeatherMediator {
    void wereWolvesTurn(List<WereWolf> wereWolves, List<Role> aliveRoles);
    void villagersTurn(List<Role> aliveRoles);
    void trigger();
}
