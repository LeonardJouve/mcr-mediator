package mediator.alternative;

import role.Role;
import role.WereWolf;

import java.util.List;
import java.util.Random;

/**
 * Mediator that runs a basic game with normal weather conditions.
 * It allows werewolves to vote to eliminate a player and villagers to do the same.
 * The weather can change randomly to either a Blood Moon or a Villager Advantage.
 */
public class NormalWeatherMediator implements WeatherMediator {
    protected final BaseRuleMediator mediator;

    public NormalWeatherMediator(BaseRuleMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void wereWolvesTurn(List<WereWolf> wereWolves, List<Role> aliveRoles) {
        // demander aux loups-garou de voter pour éliminer un joueur
        this.mediator.killVote(wereWolves, aliveRoles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void villagersTurn(List<Role> aliveRoles) {
        this.mediator.killVote(aliveRoles, aliveRoles);
    }

    /**
     * Randomly change weather to either a Blood Moon or a Villager Advantage.
     */
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
