package com.TaskManagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.TaskManagement.dto.CreateTaskRequest;
import com.TaskManagement.dto.TaskDto;
import com.TaskManagement.dto.UpdateTaskRequest;
import com.TaskManagement.entity.Task;
import com.TaskManagement.entity.User;
import com.TaskManagement.repository.TaskRepository;
import com.TaskManagement.repository.UserRepository;
import com.TaskManagement.service.TaskService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of the TaskService interface.
 * Contains the business logic for managing tasks, including security checks
 * to ensure users can only access their own tasks.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    /**
     * Creates a new task for the currently authenticated user.
     *
     * @param createTaskRequest The request object containing task details.
     * @return The created task as a Data Transfer Object (DTO).
     */
    @Override
    public TaskDto createTask(CreateTaskRequest createTaskRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Task task = Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .user(currentUser)
                .build();

        Task savedTask = taskRepository.save(task);

        return mapToDto(savedTask);
    }

    /**
     * Retrieves all tasks for the currently authenticated user, ordered by creation date descending.
     *
     * @return A list of task DTOs.
     */
    @Override
    public List<TaskDto> getAllTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        List<Task> tasks = taskRepository.findByUserIdOrderByCreatedAtDesc(currentUser.getId());

        return tasks.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a single task by its ID.
     * Ensures that the task belongs to the currently authenticated user.
     *
     * @param id The ID of the task to retrieve.
     * @return The requested task as a DTO.
     * @throws RuntimeException if the task is not found or does not belong to the current user.
     */
    @Override
    public TaskDto getTaskById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found!"));

        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Task not found!"); // Or a more specific access denied exception
        }

        return mapToDto(task);
    }

    /**
     * Updates an existing task.
     * Ensures that the task belongs to the currently authenticated user before updating.
     *
     * @param id                The ID of the task to update.
     * @param updateTaskRequest The request object containing the new task details.
     * @return The updated task as a DTO.
     * @throws RuntimeException if the task is not found or does not belong to the current user.
     */
    @Override
    public TaskDto updateTask(Long id, UpdateTaskRequest updateTaskRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found!"));

        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Task not found!"); // Or a more specific access denied exception
        }

        task.setTitle(updateTaskRequest.getTitle());
        task.setDescription(updateTaskRequest.getDescription());
        task.setStatus(updateTaskRequest.getStatus());

        Task updatedTask = taskRepository.save(task);

        return mapToDto(updatedTask);
    }

    /**
     * Deletes a task by its ID.
     * Ensures that the task belongs to the currently authenticated user before deletion.
     *
     * @param id The ID of the task to delete.
     * @throws RuntimeException if the task is not found or does not belong to the current user.
     */
    @Override
    public void deleteTask(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found!"));

        if (!task.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Task not found!"); // Or a more specific access denied exception
        }

        taskRepository.delete(task);
    }

    /**
     * Maps a Task entity to a TaskDto.
     *
     * @param task The Task entity to map.
     * @return The resulting TaskDto.
     */
    private TaskDto mapToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .userId(task.getUser().getId())
                .build();
    }
}
