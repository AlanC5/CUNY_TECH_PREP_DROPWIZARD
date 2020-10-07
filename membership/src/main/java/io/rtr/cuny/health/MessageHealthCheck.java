package io.rtr.cuny.health;

import com.codahale.metrics.health.HealthCheck;

public class MessageHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy("Nothing to check, so we are healthy!");
    }
}
