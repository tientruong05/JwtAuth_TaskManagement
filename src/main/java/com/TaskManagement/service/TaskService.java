package com.TaskManagement.service;

import java.util.List;

import com.TaskManagement.dto.CreateTaskRequest;
import com.TaskManagement.dto.TaskDto;
import com.TaskManagement.dto.UpdateTaskRequest;

/**
 * Defines the service interface for business operations related to Tasks.
 *
 * This interface defines the contract for managing tasks, including creating,
 * retrieving, updating, and deleting. Implementations must ensure that only
 * authenticated users can access and manage their own tasks.
 */
public interface TaskService {

    /**
     * Creates a new task for the currently authenticated user.
     *
     * @param createTaskRequest The request object containing the new task's details.
     * @return A {@link TaskDto} representing the newly created task.
     */
    TaskDto createTask(CreateTaskRequest createTaskRequest);

    /**
     * Retrieves all tasks belonging to the currently authenticated user.
     *
     * @return A list of {@link TaskDto}s.
     */
    List<TaskDto> getAllTasks();

    /**
     * Gets the details of a specific task by its ID.
     *
     * The implementation must validate that the task belongs to the authenticated user.
     *
     * @param id The ID of the task to retrieve.
     * @return A {@link TaskDto} containing the task's information.
     * @throws jakarta.persistence.EntityNotFoundException if the task is not found or the user does not have access.
     */
    TaskDto getTaskById(Long id);

    /**
     * Updates an existing task.
     *
     * The implementation must validate that the task belongs to the authenticated user.
     *
     * @param id                The ID of the task to update.
     * @param updateTaskRequest The request object containing the update information.
     * @return A {@link TaskDto} representing the task after the update.
     * @throws jakarta.persistence.EntityNotFoundException if the task is not found or the user does not have access.
     */
    TaskDto updateTask(Long id, UpdateTaskRequest updateTaskRequest);

    /**
     * Deletes a task by its ID.
     *
     * The implementation must validate that the task belongs to the authenticated user.
     *
     * @param id The ID of the task to delete.
     * @throws jakarta.persistence.EntityNotFoundException if the task is not found or the user does not have access.
     */
    void deleteTask(Long id);
}
