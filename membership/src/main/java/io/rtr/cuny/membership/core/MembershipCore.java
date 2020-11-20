package io.rtr.cuny.membership.core;

import io.rtr.cuny.membership.db.MembershipDAO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.List;

public class MembershipCore {
    private final MembershipDAO membershipDAO;
    private final HttpClient httpClient;

    // TODO: url should be in config.
    private static final String getUserIdUrl = "http://localhost:8880/users/userId";

    public MembershipCore(final MembershipDAO membershipDAO, HttpClient httpClient) {
        this.membershipDAO = membershipDAO;
        this.httpClient = httpClient;
    }

    public boolean memberExists(final Long userId) {
        return !membershipDAO.findByUserId(userId).isEmpty();
    }

    // TODO: client should be in clients package
    public boolean userExists(final Long userId) {
        final HttpGet request = new HttpGet(String.format("%s/%d", getUserIdUrl, userId));
        HttpResponse response;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new WebApplicationException("Error connecting to Users service.");
        }
        return response.getStatusLine().getStatusCode() == 200 && response.getEntity().getContentLength() > 0;
    }

    public List<Membership> listMembers() {
        return membershipDAO.findAll();
    }

    public Membership findMemberById(long id) {
        return membershipDAO.findById(id);
    }

    public List<Membership> findMembersByUserId(final long userId) {
        return membershipDAO.findByUserId(userId);
    }

    public Membership updateMember(final Membership memberShip) {
        return membershipDAO.update(memberShip);
    }

    public Membership createMember(final Membership memberShip) {
        return membershipDAO.create(memberShip);
    }

    public boolean deleteMemberById(final Long id) {
        return membershipDAO.delete(id) > 0;
    }
}
