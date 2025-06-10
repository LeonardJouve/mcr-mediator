package mediator.alternative;

import role.Role;
import role.WereWolf;

import java.util.List;

public class BloodMoonMediator extends NormalWeatherMediator {
    public BloodMoonMediator(BaseRuleMediator mediator) {
        super(mediator);
        this.mediator.getGameDisplay().showMessage("Une nuit de sang se lève.");
    }

    @Override
    public void wereWolvesTurn(List<WereWolf> wereWolves, List<Role> aliveRoles) {
        super.wereWolvesTurn(wereWolves, aliveRoles);
        this.mediator.getGameDisplay().showMessage("Les loups garous peuvent choisir une seconde cible.");
        super.wereWolvesTurn(wereWolves, aliveRoles);
    }

    @Override
    public void trigger() {
        this.mediator.setWeatherMediator(new NormalWeatherMediator(this.mediator));
    }
}
