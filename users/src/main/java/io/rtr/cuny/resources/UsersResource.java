package io.rtr.cuny.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.rtr.cuny.core.User;
import io.rtr.cuny.db.UserDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {
    private final UserDAO userDAO;

    public UsersResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @POST
    @UnitOfWork
    public User createUser(@Valid User user) {
        return userDAO.create(user);
    }

    @GET
    @UnitOfWork
    public List<User> listUsers() {
        return userDAO.findAll();
    }
}
