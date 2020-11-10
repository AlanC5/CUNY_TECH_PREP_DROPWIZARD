package io.rtr.cuny.users;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.rtr.cuny.users.core.User;
import io.rtr.cuny.users.db.UserDAO;
import io.rtr.cuny.users.health.UsersHealthCheck;
import io.rtr.cuny.users.resources.UsersResource;

public class UsersApplication extends Application<UsersConfiguration> {
    private final HibernateBundle<UsersConfiguration> hibernateBundle =
            new HibernateBundle<UsersConfiguration>(User.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(UsersConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(final String[] args) throws Exception {
        new UsersApplication().run(args);
    }

    @Override
    public String getName() {
        return "users";
    }

    @Override
    public void initialize(final Bootstrap<UsersConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<UsersConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(UsersConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final UsersConfiguration configuration,
                    final Environment environment) {
        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());

        final UsersHealthCheck healthCheck = new UsersHealthCheck();

        environment.healthChecks().register("message", healthCheck);
        environment.jersey().register(new UsersResource(userDAO));
    }

}
