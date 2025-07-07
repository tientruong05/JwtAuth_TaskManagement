package com.TaskManagement.service;

import com.TaskManagement.dto.JwtAuthenticationResponse;
import com.TaskManagement.dto.SignInRequest;
import com.TaskManagement.dto.SignUpRequest;
import com.TaskManagement.entity.User;

public interface AuthService {

    // Signup request
    User signup(SignUpRequest signUpRequest);

    // Signin request with Jwt token
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
}
