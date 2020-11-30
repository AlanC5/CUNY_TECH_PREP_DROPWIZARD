package io.rtr.cuny.membership;

import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.rtr.cuny.membership.models.Membership;
import io.rtr.cuny.membership.core.MembershipCore;
import io.rtr.cuny.membership.db.MembershipDAO;
import io.rtr.cuny.membership.health.MembershipHealthCheck;
import io.rtr.cuny.membership.resources.MembershipResource;
import org.apache.http.client.HttpClient;

public class MembershipApplication extends Application<MembershipConfiguration> {

    private final HibernateBundle<MembershipConfiguration> hibernateBundle =
            new HibernateBundle<MembershipConfiguration>(Membership.class) {
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
        final MembershipDAO membershipDAO = new MembershipDAO(hibernateBundle.getSessionFactory());
        final HttpClient httpClient = new HttpClientBuilder(environment).using(configuration.getHttpClientConfiguration())
                .build(getName());
        final MembershipCore membershipCore = new MembershipCore(membershipDAO, httpClient);
        final MembershipHealthCheck healthCheck = new MembershipHealthCheck();

        environment.healthChecks().register("membership", healthCheck);
        environment.jersey().register(new MembershipResource(membershipCore));
    }

}
