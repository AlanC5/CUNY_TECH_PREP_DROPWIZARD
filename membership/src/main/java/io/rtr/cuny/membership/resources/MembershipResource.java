package io.rtr.cuny.membership.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.rtr.cuny.membership.core.Membership;
import io.rtr.cuny.membership.db.MembershipDAO;
import org.apache.http.client.HttpClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/membership")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MembershipResource {
    private final MembershipDAO membershipDAO;
    private final HttpClient httpClient;

    public MembershipResource(MembershipDAO membershipDAO, HttpClient httpClient) {
        this.membershipDAO = membershipDAO;
        this.httpClient = httpClient;
    }

    @POST
    @UnitOfWork
    public Membership createMember(@Valid Membership memberShip) {
        return membershipDAO.create(memberShip);
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
