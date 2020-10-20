package io.rtr.cuny.membership.db;

import io.dropwizard.hibernate.AbstractDAO;
import io.rtr.cuny.membership.core.Membership;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MemberDAO extends AbstractDAO<Membership> {
    public MemberDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Membership findById(Long id) {
        return get(id);
    }

    public List<Membership> findByUserId(Long userId) {
        Query query = namedQuery("io.rtr.cuny.core.Member.findByUserId");
        query.setParameter("userId", userId);

        return list(query);
    }

    public Membership create(Membership memberShip) {
        return persist(memberShip);
    }

    @SuppressWarnings("unchecked")
    public List<Membership> findAll() {
        return list((Query<Membership>) namedQuery("io.rtr.cuny.membership.core.Member.findAll"));
    }

    public Membership update(Membership memberShip) {
        final Membership existingMembership = get(memberShip.getId());
        if (existingMembership == null) {
            return null;
        }
        currentSession().clear();

        return persist(memberShip);
    }
}
