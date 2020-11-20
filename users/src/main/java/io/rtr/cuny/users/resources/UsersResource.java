package io.rtr.cuny.users.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.rtr.cuny.users.core.User;
import io.rtr.cuny.users.db.UserDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response createUser(@Valid User user)
    {
        final User foundUser = userDAO.findByUserId(user.getUserId());
        if (foundUser != null) {
            return Response
                    .status(304, String.format("User for userId=%d already exists.", user.getUserId()))
                    .build();
        }
        return Response.ok(userDAO.create(user)).build();
    }

    @GET
    @UnitOfWork
    public List<User> listUsers() {
        return userDAO.findAll();
    }

    @GET
    @UnitOfWork
    @Path("/userId/{userId}")
    public User findUserByUserId(@PathParam("userId") long userId) {
        return userDAO.findByUserId(userId);
    }

    @PATCH
    @UnitOfWork
    public User updateUser(@Valid User user) {
        return userDAO.update(user);
    }
}
