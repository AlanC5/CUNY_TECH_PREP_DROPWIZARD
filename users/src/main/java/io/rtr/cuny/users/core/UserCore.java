package io.rtr.cuny.users.core;

import io.rtr.cuny.users.db.UserDAO;
import io.rtr.cuny.users.models.User;

import java.util.List;

public class UserCore {
    private final UserDAO userDAO;

    public UserCore(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findByUserId(final Long userId) {
        return userDAO.findByUserId(userId);
    }

    public List<User> listUsers() {
        return userDAO.findAll();
    }

    public User updateUser(final User user) {
        return userDAO.update(user);
    }

    public User createUser(final User user) {
        return userDAO.create(user);
    }

    public boolean deleteById(final Long id) {
        //TODO: delete any associated memberships
        return userDAO.deleteUserById(id) > 0;
    }
}
