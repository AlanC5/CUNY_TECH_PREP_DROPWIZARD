package io.rtr.cuny.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.rtr.cuny.core.Member;
import io.rtr.cuny.db.MemberDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MembersResource {
    private final MemberDAO memberDAO;

    public MembersResource(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @POST
    @UnitOfWork
    public Member createMember(@Valid Member member) {
        return memberDAO.create(member);
    }

    @GET
    @UnitOfWork
    public List<Member> listMembers() {
        return memberDAO.findAll();
    }
}
