package io.rtr.cuny.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "users")
@NamedQueries(
        {
                @NamedQuery(
                        name = "io.rtr.cuny.core.User.findAll",
                        query = "SELECT u FROM User u"
                )
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private @NotNull Long userId;

    public User() {
    }

    public User(long id, @NotNull Long userId) {
        this.id = id;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
