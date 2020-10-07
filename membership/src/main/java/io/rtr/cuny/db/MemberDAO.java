package io.rtr.cuny.db;

import io.dropwizard.hibernate.AbstractDAO;
import io.rtr.cuny.core.Member;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MemberDAO extends AbstractDAO<Member> {
    public MemberDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Member findById(Long id) {
        return get(id);
    }

    public Member create(Member member) {
        return persist(member);
    }

    @SuppressWarnings("unchecked")
    public List<Member> findAll() {
        return list((Query<Member>) namedQuery("io.rtr.cuny.core.Member.findAll"));
    }
}
