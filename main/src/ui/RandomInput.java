package ui;

import role.Role;

import java.util.List;
import java.util.Random;

public class RandomInput implements UserInput {
    Random r = new Random();

    @Override
    public boolean askHeal() {
        return r.nextBoolean();
    }

    @Override
    public boolean askKill() {
        return r.nextBoolean();
    }

    @Override
    public Role selectRole(List<Role> roles) {
        return roles.get(r.nextInt(roles.size()));
    }
}