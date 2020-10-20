package io.rtr.cuny.membership;

import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.rtr.cuny.membership.core.Membership;
import io.rtr.cuny.membership.db.MemberDAO;
import io.rtr.cuny.membership.health.MembershipHealthCheck;
import io.rtr.cuny.membership.resources.MembersResource;
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
        final MemberDAO memberDAO = new MemberDAO(hibernateBundle.getSessionFactory());
        final HttpClient httpClient = new HttpClientBuilder(environment).using(configuration.getHttpClientConfiguration())
            .build(getName());
        final MembershipHealthCheck healthCheck = new MembershipHealthCheck();


        environment.healthChecks().register("membership", healthCheck);
        environment.jersey().register(new MembersResource(memberDAO, httpClient));
    }

}
