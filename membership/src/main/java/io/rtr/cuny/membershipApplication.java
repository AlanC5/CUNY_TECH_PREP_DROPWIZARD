package io.rtr.cuny;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.rtr.cuny.health.MessageHealthCheck;
import io.rtr.cuny.resources.MessageResource;

public class membershipApplication extends Application<membershipConfiguration> {

    public static void main(final String[] args) throws Exception {
        new membershipApplication().run(args);
    }

    @Override
    public String getName() {
        return "membership";
    }

    @Override
    public void initialize(final Bootstrap<membershipConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final membershipConfiguration configuration,
                    final Environment environment) {
        final MessageResource messageResource = new MessageResource(configuration.getMessage());
        final MessageHealthCheck healthCheck = new MessageHealthCheck();

        environment.healthChecks().register("message", healthCheck);
        environment.jersey().register(messageResource);
    }

}
