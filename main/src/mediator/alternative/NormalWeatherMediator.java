package mediator.alternative;

import role.Role;
import role.WereWolf;

import java.util.List;
import java.util.Random;

public class NormalWeatherMediator implements WeatherMediator {
    protected final BaseRuleMediator mediator;

    public NormalWeatherMediator(BaseRuleMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void wereWolvesTurn(List<WereWolf> wereWolves, List<Role> aliveRoles) {
        // demander aux loups-garou de voter pour éliminer un joueur
        this.mediator.killVote(wereWolves, aliveRoles);
    }

    @Override
    public void villagersTurn(List<Role> aliveRoles) {
        this.mediator.killVote(aliveRoles, aliveRoles);
    }

    @Override
    public void trigger() {
        //
        Random rand = new Random();
        if (rand.nextInt(2) == 1) {
            this.mediator.setWeatherMediator(new BloodMoonMediator(mediator));
            return;
        }

        if (rand.nextInt(2) == 1) {
            this.mediator.setWeatherMediator(new VillagerAdvantageMediator(mediator));
            return;
        }

        this.mediator.getGameDisplay().showMessage("Le temps est clair.");
    }
}
