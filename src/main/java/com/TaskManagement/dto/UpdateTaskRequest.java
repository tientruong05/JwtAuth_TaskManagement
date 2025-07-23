package com.TaskManagement.dto;

import com.TaskManagement.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request payload for updating an existing task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {

    /**
     * The new title for the task.
     */
    private String title;

    /**
     * The new description for the task.
     */
    private String description;

    /**
     * The new status for the task.
     */
    private TaskStatus status;
}
