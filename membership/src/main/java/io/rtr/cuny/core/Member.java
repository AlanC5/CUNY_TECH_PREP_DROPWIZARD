package io.rtr.cuny.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

enum MembershipType {
    BRONZE,
    SILVER,
    GOLD
}

@Entity
@Table(name = "members")
@NamedQueries(
        {
                @NamedQuery(
                        name = "io.rtr.cuny.core.Member.findAll",
                        query = "SELECT m FROM Member m"
                )
        })
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "active")
    private boolean active;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private MembershipType type;

    public Member(long id, boolean active, MembershipType type) {
        this.id = id;
        this.active = active;
        this.type = type;
    }

    public Member() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id &&
                active == member.active &&
                type == member.type;
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
