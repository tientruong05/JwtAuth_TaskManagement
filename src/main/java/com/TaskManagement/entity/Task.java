package com.TaskManagement.entity;

import com.TaskManagement.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Represents a task entity in the database.
 */
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    /**
     * The unique identifier for the task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the task. Cannot be null and has a maximum length of 100 characters.
     */
    @Column(nullable = false, length = 100)
    private String title;

    /**
     * A detailed description of the task. Mapped as a Large Object (LOB).
     */
    @Lob
    private String description;

    /**
     * The current status of the task (e.g., PENDING, IN_PROGRESS, COMPLETED).
     * Defaults to PENDING.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TaskStatus status = TaskStatus.PENDING;

    /**
     * The timestamp when the task was created. Automatically set on creation.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the task was last updated. Automatically set on update.
     */
    @UpdateTimestamp
    @Column(name = "updated_at", updatable = false)
    private LocalDateTime updatedAt;

    /**
     * The user to whom the task is assigned.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;
}
