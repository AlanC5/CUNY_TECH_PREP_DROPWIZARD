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
    @Column(name = "type", nullable = false)
    private MembershipType type;

    public Membership(long id, boolean active, MembershipType type) {
        this.id = id;
        this.active = active;
        this.type = type;
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
                type == memberShip.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, type);
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

    public MembershipType getType() {
        return type;
    }

    public void setType(MembershipType type) {
        this.type = type;
    }
}
