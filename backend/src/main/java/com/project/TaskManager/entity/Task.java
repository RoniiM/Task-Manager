package com.project.TaskManager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.TaskManager.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @Column(length = 500)
    private String description;

    private boolean completed;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;
}
