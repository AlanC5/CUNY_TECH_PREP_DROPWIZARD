package io.rtr.cuny;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class UsersApplication extends Application<UsersConfiguration> {

    public static void main(final String[] args) throws Exception {
        new UsersApplication().run(args);
    }

    @Override
    public String getName() {
        return "users";
    }

    @Override
    public void initialize(final Bootstrap<UsersConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final UsersConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
