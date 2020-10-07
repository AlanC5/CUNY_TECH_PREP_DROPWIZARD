package io.rtr.cuny;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class usersApplication extends Application<usersConfiguration> {

    public static void main(final String[] args) throws Exception {
        new usersApplication().run(args);
    }

    @Override
    public String getName() {
        return "users";
    }

    @Override
    public void initialize(final Bootstrap<usersConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final usersConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
