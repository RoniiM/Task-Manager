package com.project.TaskManager.dto;

import com.project.TaskManager.enums.Priority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest
{
    private int id;

    @NotBlank(message = "Title cannot be empty.")
    @Size(max = 200, message = "Title must be less than 200 characters long.")
    private String title;

    @Size(max = 500, message = "Description must be less than 500 characters long.")
    private String description;

    @NotNull(message = "Completion status is required.")
    private Boolean completed;

    @NotNull(message = "Priority level is required.")
    private Priority priority;

    @FutureOrPresent(message = "Invalid date, only future dates allowed.")
    private LocalDate dueDate;
}
