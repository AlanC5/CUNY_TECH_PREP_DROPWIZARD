package io.rtr.cuny.users.health;

import com.codahale.metrics.health.HealthCheck;

public class UsersHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy("Nothing to check, so we are healthy!");

    }
}
