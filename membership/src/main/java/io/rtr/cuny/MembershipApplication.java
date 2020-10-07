package io.rtr.cuny;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.rtr.cuny.core.Member;
import io.rtr.cuny.db.MemberDAO;
import io.rtr.cuny.health.MessageHealthCheck;
import io.rtr.cuny.resources.MembersResource;
import io.rtr.cuny.resources.MessageResource;

public class MembershipApplication extends Application<MembershipConfiguration> {

    private final HibernateBundle<MembershipConfiguration> hibernateBundle =
            new HibernateBundle<MembershipConfiguration>(Member.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(MembershipConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(final String[] args) throws Exception {
        new MembershipApplication().run(args);
    }

    @Override
    public String getName() {
        return "membership";
    }

    @Override
    public void initialize(final Bootstrap<MembershipConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<MembershipConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(MembershipConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final MembershipConfiguration configuration,
                    final Environment environment) {
        final MemberDAO memberDAO = new MemberDAO(hibernateBundle.getSessionFactory());

        final MessageHealthCheck healthCheck = new MessageHealthCheck();

        environment.healthChecks().register("message", healthCheck);
        environment.jersey().register(new MessageResource(configuration.getMessage()));
        environment.jersey().register(new MembersResource(memberDAO));
    }

}
