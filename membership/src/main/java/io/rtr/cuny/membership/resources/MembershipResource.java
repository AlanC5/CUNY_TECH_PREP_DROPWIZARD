package io.rtr.cuny.membership.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.rtr.cuny.membership.core.Membership;
import io.rtr.cuny.membership.models.MembershipCore;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/membership")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MembershipResource {
    private final MembershipCore membershipCore;

    public MembershipResource(MembershipCore membershipCore) {
        this.membershipCore = membershipCore;
    }

    @POST
    @UnitOfWork
    public Response createMember(@Valid Membership memberShip) {
        // TODO: make a validator
        if (membershipCore.memberExists(memberShip.getUserId())) {
            return Response
                    .status(304, String.format("Membership for userId=%d already exists.", memberShip.getUserId()))
                    .build();
        }
        if (!membershipCore.userExists(memberShip.getUserId())) {
            return Response
                .status(304, String.format("Could not find user account for userId=%d.", memberShip.getUserId()))
                .build();
        }
        return Response.ok(membershipCore.createMember(memberShip)).build();
    }

    @GET
    @UnitOfWork
    public Response listMembers() {
        return Response.ok(membershipCore.listMembers()).build();
    }

    @GET
    @UnitOfWork
    @Path("{id}")
    public Response findMemberById(@PathParam("id") long id) {
        return Response.ok(membershipCore.findMemberById(id)).build();
    }

    @GET
    @UnitOfWork
    @Path("/userId/{userId}")
    public Response findMembersByUserId(@PathParam("userId") long userId) {
        return Response.ok(membershipCore.findMembersByUserId(userId)).build();
    }

    @PATCH
    @UnitOfWork
    public Response updateMember(@Valid Membership memberShip) {
        return Response.ok(membershipCore.updateMember(memberShip)).build();
    }

    @DELETE
    @UnitOfWork
    @Path("{id}")
    public Response deleteMemberById(@PathParam("id") long id) {
        return Response.ok(membershipCore.deleteMemberById(id)).build();
    }
}
