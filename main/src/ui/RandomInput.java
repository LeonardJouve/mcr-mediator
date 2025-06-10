package ui;

import role.Role;

import java.util.List;
import java.util.Random;

/**
 * Class that simulates user choices with pure randomness.
 */
public class RandomInput implements UserInput {
    Random r = new Random();

    /**
     * {@inheritDoc}
     * Here this is done randomly.
     */
    @Override
    public boolean askHeal() {
        return r.nextBoolean();
    }

    /**
     * {@inheritDoc}
     * Here this is done randomly.
     */
    @Override
    public boolean askKill() {
        return r.nextBoolean();
    }

    /**
     * {@inheritDoc}
     * Here this is done randomly.
     */
    @Override
    public Role selectRole(List<Role> roles, String reason) {
        return roles.get(r.nextInt(roles.size()));
    }
}