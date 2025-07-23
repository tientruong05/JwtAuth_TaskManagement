package com.TaskManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.dto.CreateTaskRequest;
import com.TaskManagement.dto.TaskDto;
import com.TaskManagement.dto.UpdateTaskRequest;
import com.TaskManagement.service.TaskService;

import lombok.RequiredArgsConstructor;

/**
 * REST controller for managing tasks.
 * Provides endpoints for creating, retrieving, updating, and deleting tasks.
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    /**
     * Creates a new task.
     *
     * @param createTaskRequest The request body containing the details for the new task.
     * @return A ResponseEntity containing the created task and HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        TaskDto createdTask = taskService.createTask(createTaskRequest);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    /**
     * Retrieves all tasks.
     *
     * @return A ResponseEntity containing a list of all tasks and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Retrieves a specific task by its ID.
     *
     * @param id The ID of the task to retrieve.
     * @return A ResponseEntity containing the requested task and HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        TaskDto task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Updates an existing task.
     *
     * @param id                The ID of the task to update.
     * @param updateTaskRequest The request body containing the updated details for the task.
     * @return A ResponseEntity containing the updated task and HTTP status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequest updateTaskRequest) {
        TaskDto updatedTask = taskService.updateTask(id, updateTaskRequest);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to delete.
     * @return A ResponseEntity with HTTP status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
