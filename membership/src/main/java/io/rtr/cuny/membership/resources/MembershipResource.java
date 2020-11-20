package io.rtr.cuny.membership.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.rtr.cuny.membership.core.Membership;
import io.rtr.cuny.membership.db.MembershipDAO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/membership")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MembershipResource {
    private final MembershipDAO membershipDAO;
    private final HttpClient httpClient;

    // TODO: url should be in config.
    private static final String getUserIdUrl = "http://localhost:8880/users/userId";

    public MembershipResource(MembershipDAO membershipDAO, HttpClient httpClient) {
        this.membershipDAO = membershipDAO;
        this.httpClient = httpClient;
    }

    @POST
    @UnitOfWork
    public Response createMember(@Valid Membership memberShip) throws IOException {
        // TODO: move this to MembershipCore -- need dependency injection
        List<Membership> memberships = membershipDAO.findByUserId(memberShip.getUserId());
        if (!memberships.isEmpty()) {
            return Response
                    .status(304, String.format("Membership for userId=%d already exists.", memberShip.getUserId()))
                    .build();
        }
        final HttpGet request = new HttpGet(String.format("%s/%d", getUserIdUrl, memberShip.getUserId()));
        HttpResponse response;
        try {
            response = httpClient.execute(request);
        } catch (java.net.ConnectException e) {
            return Response
                    .status(304, String.format("Could not access Users service for userId=%d", memberShip.getUserId()))
                    .build();
        }

        if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 300) {
            return Response
                    .status(304, String.format("Could not find user account for userId=%d.", memberShip.getUserId()))
                    .build();
        }

        membershipDAO.create(memberShip);
        return Response
                .ok(memberShip).build();
    }

    @GET
    @UnitOfWork
    public List<Membership> listMembers() {
        return membershipDAO.findAll();
    }

    @GET
    @UnitOfWork
    @Path("{id}")
    public Membership findMemberById(@PathParam("id") long id) {
        return membershipDAO.findById(id);
    }

    @GET
    @UnitOfWork
    @Path("/userId/{userId}")
    public List<Membership> findMembersByUserId(@PathParam("userId") long userId) {
        return membershipDAO.findByUserId(userId);
    }

    @PATCH
    @UnitOfWork
    public Membership updateMember(@Valid Membership memberShip) {
        return membershipDAO.update(memberShip);
    }
}
