package io.rtr.cuny.users.db;

import io.dropwizard.hibernate.AbstractDAO;
import io.rtr.cuny.users.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findById(Long id) {
        return get(id);
    }

    public User findByUserId(Long userId) {
        Query query = namedQuery("io.rtr.cuny.users.models.User.findByUserId");
        query.setParameter("userId", userId);

        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User create(User user) {
        return persist(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return list((Query<User>) namedQuery("io.rtr.cuny.users.models.User.findAll"));
    }

    public User update(User user) {
        final User existingUser = findById(user.getId());
        if (existingUser == null) {
            return null;
        }
        currentSession().clear();

        return persist(user);
    }

    public int deleteUserById(final Long id) {
        Query query = namedQuery("io.rtr.cuny.users.models.User.deleteById");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
