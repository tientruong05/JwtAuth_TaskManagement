package com.TaskManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request payload for creating a new task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {

    /**
     * The title of the new task.
     */
    private String title;

    /**
     * The description of the new task.
     */
    private String description;
}
