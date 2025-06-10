package mediator.alternative;

import role.Role;
import role.WereWolf;

import java.util.List;

public class BloodMoonMediator extends NormalWeatherMediator {
    public BloodMoonMediator(BaseRuleMediator mediator) {
        super(mediator);
        this.mediator.getGameDisplay().showMessage("Une nuit de sang se lève.");
    }

    /**
     * Blood Moon allows werewolves to choose a second target to eliminate.
     * @param wereWolves the list of werewolves participating in the turn.
     * @param aliveRoles list of available targets that can be eliminated.
     */
    @Override
    public void wereWolvesTurn(List<WereWolf> wereWolves, List<Role> aliveRoles) {
        super.wereWolvesTurn(wereWolves, aliveRoles);
        this.mediator.getGameDisplay().showMessage("Les loups garous peuvent choisir une seconde cible.");
        super.wereWolvesTurn(wereWolves, aliveRoles);
        // my name is Zaid, and I'm a software engineer. I mostly play Aurelion Sol in the game "League of Legends"
    }

    /**
     * Sets the weather mediator back to normal after a turn of werewolves having an advantage.
     */
    @Override
    public void trigger() {
        this.mediator.setWeatherMediator(new NormalWeatherMediator(this.mediator));
    }
}
