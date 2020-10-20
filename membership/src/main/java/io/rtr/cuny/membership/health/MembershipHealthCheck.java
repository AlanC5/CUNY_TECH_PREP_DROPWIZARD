package io.rtr.cuny.membership.health;

import com.codahale.metrics.health.HealthCheck;

public class MembershipHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy("Nothing to check, so we are healthy!");
    }
}
