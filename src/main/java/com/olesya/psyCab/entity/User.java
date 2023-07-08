package com.olesya.psyCab.entity;

import com.olesya.psyCab.email.ValidEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="username", unique = true)
    private String username;

    @ValidEmail
    @Size(min = 1, message = "{size.user.email}")
    private String email;
    private String password;

    @Column(name = "enabled")
    private boolean isEnabled;

    @Column(name="name")
    private String firstName;

    @Column(name="surname")
    private String lastName;

    @Column(name="phone")
    private Long phoneNumber;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
    private Set<Role> roles;

    public User(String username, String email, String password, boolean isEnabled, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEnabled = false;
        this.roles = roles;
    }

    public User(String username, String email, String password, boolean isEnabled) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isEnabled = false;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", roles=" + roles +
                '}';
    }
}
