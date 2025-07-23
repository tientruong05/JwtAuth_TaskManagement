package com.TaskManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request payload for user sign-up.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    /**
     * The desired username for the new account.
     */
    private String username;

    /**
     * The password for the new account.
     */
    private String password;

    /**
     * The email address for the new account.
     */
    private String email;
}
