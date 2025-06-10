package mediator.alternative;

import role.Role;

import java.util.List;

public class VillagerAdvantageMediator extends NormalWeatherMediator {
    public VillagerAdvantageMediator(BaseRuleMediator mediator) {
        super(mediator);
        this.mediator.getGameDisplay().showMessage("Les villageois sont touchés par la grâce divine.");
    }

    @Override
    public void villagersTurn(List<Role> aliveRoles) {
        Role role = this.mediator.killVote(aliveRoles, aliveRoles);
        if (role.isWereWolf()) {
            this.mediator.getGameDisplay().showRoleReveal(role);
            this.mediator.killVote(aliveRoles, aliveRoles);
        }
    }

    @Override
    public void trigger() {
        this.mediator.setWeatherMediator(new NormalWeatherMediator(this.mediator));
    }
}