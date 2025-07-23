package com.TaskManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request payload for user sign-in.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    /**
     * The username of the user attempting to sign in.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;
}
