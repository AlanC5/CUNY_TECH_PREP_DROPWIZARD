package io.rtr.cuny.membership.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.rtr.cuny.membership.core.Membership;
import io.rtr.cuny.membership.db.MemberDAO;
import org.apache.http.client.HttpClient;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MembersResource {
    private final MemberDAO memberDAO;
    private final HttpClient httpClient;

    public MembersResource(MemberDAO memberDAO, HttpClient httpClient) {
        this.memberDAO = memberDAO;
        this.httpClient = httpClient;
    }

    @POST
    @UnitOfWork
    public Membership createMember(@Valid Membership memberShip) {
        return memberDAO.create(memberShip);
    }

    @GET
    @UnitOfWork
    public List<Membership> listMembers() {
        return memberDAO.findAll();
    }

    @GET
    @UnitOfWork
    @Path("{id}")
    public Membership findMemberById(@PathParam("id") long id) {
        return memberDAO.findById(id);
    }

    @GET
    @UnitOfWork
    @Path("/userId/{userId}")
    public List<Membership> findMembersByUserId(@PathParam("userId") long userId) {
        return memberDAO.findByUserId(userId);
    }

    @PATCH
    @UnitOfWork
    public Membership updateMember(@Valid Membership memberShip) {
        return memberDAO.update(memberShip);
    }
}
