package mediator.alternative;

import role.Role;

import java.util.List;

/**
 * Mediator that gives an advantage to villagers by allowing them to vote twice
 * if a werewolf is targeted.
 */
public class VillagerAdvantageMediator extends NormalWeatherMediator {
    public VillagerAdvantageMediator(BaseRuleMediator mediator) {
        super(mediator);
        this.mediator.getGameDisplay().showMessage("Les villageois sont touchés par la grâce divine.");
    }

    /**
     * Villagers' turn where they can vote to eliminate a player.
     * If a werewolf is targeted, villagers get to vote again.
     * @param aliveRoles list of available targets that can be eliminated.
     */
    @Override
    public void villagersTurn(List<Role> aliveRoles) {
        Role role = this.mediator.killVote(aliveRoles, aliveRoles);
        if (role.isWereWolf()) {
            this.mediator.getGameDisplay().showRoleReveal(role);
            this.mediator.killVote(aliveRoles, aliveRoles);
        }
    }

    /**
     * Changes the weather mediator back to normal after a turn of villages having an advantage.
     */
    @Override
    public void trigger() {
        this.mediator.setWeatherMediator(new NormalWeatherMediator(this.mediator));
    }
}