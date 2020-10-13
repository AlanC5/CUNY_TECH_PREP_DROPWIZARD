package io.rtr.cuny.db;

import io.dropwizard.hibernate.AbstractDAO;
import io.rtr.cuny.core.Member;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MemberDAO extends AbstractDAO<Member> {
    public MemberDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Member findById(Long id) {
        return get(id);
    }

    public List<Member> findByUserId(Long userId) {
        Query query = namedQuery("io.rtr.cuny.core.Member.findByUserId");
        query.setParameter("userId", userId);

        return list(query);
    }

    public Member create(Member member) {
        return persist(member);
    }

    @SuppressWarnings("unchecked")
    public List<Member> findAll() {
        return list((Query<Member>) namedQuery("io.rtr.cuny.core.Member.findAll"));
    }

    public Member update(Member member) {
        final Member existingMember = get(member.getId());
        if (existingMember == null) {
            return null;
        }
        currentSession().clear();

        return persist(member);
    }
}
