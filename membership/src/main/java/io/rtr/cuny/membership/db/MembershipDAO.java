package io.rtr.cuny.membership.db;

import io.dropwizard.hibernate.AbstractDAO;
import io.rtr.cuny.membership.core.Membership;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MembershipDAO extends AbstractDAO<Membership> {
    public MembershipDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Membership findById(Long id) {
        return get(id);
    }

    public List<Membership> findByUserId(Long userId) {
        Query query = namedQuery("io.rtr.cuny.membership.core.Membership.findByUserId");
        query.setParameter("userId", userId);

        return list(query);
    }

    public Membership create(Membership memberShip) {
        return persist(memberShip);
    }

    @SuppressWarnings("unchecked")
    public List<Membership> findAll() {
        return list((Query<Membership>) namedQuery("io.rtr.cuny.membership.core.Membership.findAll"));
    }

    public Membership update(Membership memberShip) {
        final Membership existingMembership = get(memberShip.getId());
        if (existingMembership == null) {
            return null;
        }
        currentSession().clear();
        return persist(memberShip);
    }

    public int delete(final Long id) {
        Query query = namedQuery("io.rtr.cuny.membership.core.Membership.deleteById");
        query.setParameter("id", id);

        return query.executeUpdate();
    }
}
