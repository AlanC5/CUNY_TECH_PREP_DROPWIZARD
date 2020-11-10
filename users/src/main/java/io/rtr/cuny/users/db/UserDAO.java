package io.rtr.cuny.users.db;

import io.dropwizard.hibernate.AbstractDAO;
import io.rtr.cuny.users.core.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findById(Long id) {
        return get(id);
    }

    public User findByUserId(Long userId) {
        Query query = namedQuery("io.rtr.cuny.users.core.User.findByUserId");
        query.setParameter("userId", userId);

        return (User) query.getSingleResult();
    }

    public User create(User user) {
        return persist(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return list((Query<User>) namedQuery("io.rtr.cuny.users.core.User.findAll"));
    }

    public User update(User user) {
        final User existingUser = findById(user.getId());
        if (existingUser == null) {
            return null;
        }
        currentSession().clear();

        return persist(user);
    }
}
