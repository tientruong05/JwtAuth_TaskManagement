package com.TaskManagement.service;

import com.TaskManagement.dto.JwtAuthenticationResponse;
import com.TaskManagement.dto.SignInRequest;
import com.TaskManagement.dto.SignUpRequest;
import com.TaskManagement.entity.User;

/**
 * Defines the contract for user authentication services.
 *
 * Provides methods for handling user registration and sign-in.
 */
public interface AuthService {

    /**
     * Registers a new user based on the provided information.
     *
     * @param signUpRequest The request object containing user registration details.
     * @return The created {@link User} entity, saved to the database.
     */
    User signup(SignUpRequest signUpRequest);

    /**
     * Authenticates a user's credentials and generates a JWT upon success.
     *
     * @param signInRequest The request object containing login credentials (username and password).
     * @return A {@link JwtAuthenticationResponse} object containing the access token.
     */
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
}
