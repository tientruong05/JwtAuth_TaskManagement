package com.TaskManagement.dto;

import com.TaskManagement.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents the data transfer object (DTO) for a Task.
 * This is the version of the Task object that is exposed to the client.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    /**
     * The unique identifier of the task.
     */
    private Long id;

    /**
     * The title of the task.
     */
    private String title;

    /**
     * The detailed description of the task.
     */
    private String description;

    /**
     * The ID of the user to whom the task is assigned.
     */
    private Long userId;

    /**
     * The current status of the task.
     */
    private TaskStatus status;

    /**
     * The timestamp when the task was created.
     */
    private LocalDateTime createdAt;

    /**
     * The timestamp when the task was last updated.
     */
    private LocalDateTime updatedAt;
}
