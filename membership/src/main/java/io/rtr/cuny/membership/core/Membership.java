package io.rtr.cuny.membership.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

enum MembershipType {
    BRONZE,
    SILVER,
    GOLD
}

@Entity
@Table(name = "membership")
@NamedQueries(
        {
                @NamedQuery(
                        name = "io.rtr.cuny.membership.core.Membership.findAll",
                        query = "SELECT m FROM Membership m"
                ),
                @NamedQuery(
                        name = "io.rtr.cuny.membership.core.Membership.findByUserId",
                        query = "SELECT m from Membership m where m.userId = :userId"
                ),
                @NamedQuery(
                        name = "io.rtr.cuny.membership.core.Membership.deleteById",
                        query = "DELETE from Membership where id = :id"
                )
        })
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private @NotNull Long userId;

    @Column(name = "active")
    private boolean active;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "membership_type", nullable = false)
    private MembershipType membershipType;

    public Membership(long id, boolean active, MembershipType membershipType) {
        this.id = id;
        this.active = active;
        this.membershipType = membershipType;
    }

    public Membership() {
    }

    public @NotNull Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership memberShip = (Membership) o;
        return id == memberShip.id &&
                active == memberShip.active &&
                membershipType == memberShip.membershipType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, membershipType);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }
}
