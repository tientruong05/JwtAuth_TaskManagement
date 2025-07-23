package com.TaskManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents a user in the application.
 * This class is a JPA entity mapped to the "users" table and also implements
 * Spring Security's UserDetails interface to integrate with the authentication mechanism.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username, which must be unique and is used for login.
     */
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    /**
     * The user's password, which is stored in an encrypted format.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The user's email address, which must be unique.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    /**
     * The timestamp when the user account was created.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * The list of tasks associated with this user.
     * The relationship is one-to-many, and tasks are removed if the user is deleted.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Task> tasks = new ArrayList<>();


    // UserDetails Overridden Methods

    /**
     * Returns the authorities granted to the user.
     * In this implementation, all users are granted the "USER" role.
     * @return A collection of authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    /**
     * Indicates whether the user's account has expired.
     * @return true if the account is valid (non-expired), false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // Account never expires
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * @return true if the user is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked(){
        return true; // Account is never locked
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * @return true if the credentials are valid (non-expired), false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials never expire
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * @return true if the user is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true; // User is always enabled
    }
}
