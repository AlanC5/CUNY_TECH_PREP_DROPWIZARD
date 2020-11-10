package io.rtr.cuny.users.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "users")
@NamedQueries(
        {
                @NamedQuery(
                        name = "io.rtr.cuny.users.core.User.findAll",
                        query = "SELECT u FROM User u"
                ),
                @NamedQuery(
                        name = "io.rtr.cuny.users.core.User.findByUserId",
                        query = "SELECT u from User u where u.userId= :userId"
                )
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private @NotNull Long userId;

    @Column(name = "full_name")
    private String fullName;

    public User() {
    }

    public User(long id, @NotNull Long userId, @NotNull String fullName) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                userId.equals(user.userId) &&
                fullName.equals(user.fullName);
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
