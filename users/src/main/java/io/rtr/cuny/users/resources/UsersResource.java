package io.rtr.cuny.users.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.rtr.cuny.users.core.UserCore;
import io.rtr.cuny.users.models.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {
    private final UserCore userCore;

    public UsersResource(UserCore userCore) {
        this.userCore = userCore;
    }

    @POST
    @UnitOfWork
    public Response createUser(@Valid User user)
    {
        final User foundUser = userCore.findByUserId(user.getUserId());
        if (foundUser != null) {
            return Response
                    .status(304, String.format("User for userId=%d already exists.", user.getUserId()))
                    .build();
        }
        return Response.ok(userCore.createUser(user)).build();
    }

    @GET
    @UnitOfWork
    public Response listUsers() {
        return Response.ok(userCore.listUsers()).build();
    }

    @GET
    @UnitOfWork
    @Path("/userId/{userId}")
    public Response findUserByUserId(@PathParam("userId") long userId) {
        return Response.ok(userCore.findByUserId(userId)).build();
    }

    @PATCH
    @UnitOfWork
    public Response updateUser(@Valid User user) {
        return Response.ok(userCore.updateUser(user)).build();
    }

    @DELETE
    @UnitOfWork
    @Path("{id}")
    public Response deleteUserById(@PathParam("id") long id) {
        return Response.ok(userCore.deleteById(id)).build();
    }
}
