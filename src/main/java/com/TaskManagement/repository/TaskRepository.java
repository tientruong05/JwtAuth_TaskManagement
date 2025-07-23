package com.TaskManagement.repository;

import com.TaskManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Task} entities.
 *
 * This interface provides CRUD (Create, Read, Update, Delete) operations for Task objects
 * by extending JpaRepository. It also includes custom query methods for task-specific
 * data retrieval.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Finds all tasks assigned to a specific user, ordered by creation date in descending order.
     *
     * Spring Data JPA automatically generates the query from this method's name.
     *
     * @param userId The ID of the user whose tasks are to be retrieved.
     * @return A list of tasks for the specified user, sorted from newest to oldest.
     */
    List<Task> findByUserIdOrderByCreatedAtDesc(Long userId);
}
