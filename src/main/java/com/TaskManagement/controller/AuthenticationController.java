package com.TaskManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.dto.JwtAuthenticationResponse;
import com.TaskManagement.dto.SignInRequest;
import com.TaskManagement.dto.SignUpRequest;
import com.TaskManagement.service.AuthService;

import lombok.RequiredArgsConstructor;

/**
 * Controller for handling user authentication requests, such as sign-up and sign-in.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    /**
     * Registers a new user in the system.
     *
     * @param request The sign-up request containing user details (username, password, email).
     * @return A response entity with a success message.
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("User registered successfully");
    }

    /**
     * Authenticates a user and returns a JWT.
     *
     * @param request The sign-in request containing user credentials (username, password).
     * @return A response entity containing the JWT authentication response.
     */
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }
}
