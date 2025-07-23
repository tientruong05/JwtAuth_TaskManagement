package com.TaskManagement.repository;

import com.TaskManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link User} entities.
 *
 * This interface provides methods to access and manage user data,
 * including basic CRUD operations through JpaRepository and custom query methods
 * to find and check for the existence of users. It plays a central role
 * in authentication and user management.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * This method is crucial for Spring Security to load user details
     * during the authentication process. It returns an {@link Optional} to safely
     * handle cases where the user is not found.
     *
     * @param username The username of the user to find.
     * @return An {@link Optional} containing the {@link User} entity if found,
     *         or an empty Optional otherwise.
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user exists with the given username.
     *
     * This method is more efficient than fetching the entire entity just to check for existence,
     * and is typically used during registration to validate username uniqueness.
     *
     * @param username The username to check.
     * @return {@code true} if the username already exists, {@code false} otherwise.
     */
    Boolean existsByUsername(String username);

    /**
     * Checks if a user exists with the given email address.
     *
     * Similar to {@code existsByUsername}, this method is used to validate
     * email uniqueness during the registration process.
     *
     * @param email The email address to check.
     * @return {@code true} if the email already exists, {@code false} otherwise.
     */
    Boolean existsByEmail(String email);
}
