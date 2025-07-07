package com.TaskManagement.service.impl;

import com.TaskManagement.dto.JwtAuthenticationResponse;
import com.TaskManagement.dto.SignInRequest;
import com.TaskManagement.dto.SignUpRequest;
import com.TaskManagement.entity.User;
import com.TaskManagement.repository.UserRepository;
import com.TaskManagement.service.AuthService;
import com.TaskManagement.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public User signup(SignUpRequest signUpRequest) {
        // Check if email already exist
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }

        // Check if username already exist
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username already in use!");
        }

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword())) // Encrypt passwords
                .build();

        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {
        // User authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        signInRequest.getPassword()
                )
        );

        // If no exception is thrown from authenticate(), authentication is successful

        // Find user from database
        User user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Username not found!"));

        // Generate JWT token
        String jwt = jwtService.generateToken(user);

        // Return response with JWT token
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
