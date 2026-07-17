package com.project.TaskManager.controller;

import com.project.TaskManager.dto.Response;
import com.project.TaskManager.dto.TaskRequest;
import com.project.TaskManager.entity.Task;
import com.project.TaskManager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController
{
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Response<Task>> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }

    @PutMapping
    public ResponseEntity<Response<Task>> updateTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.updateTask(taskRequest));
    }

    @GetMapping
    public ResponseEntity<Response<List<Task>>> getAllUserTasks() {
        return ResponseEntity.ok(taskService.getAllUserTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Task>> getTaskById(@PathVariable int id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteTask(@PathVariable int id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @GetMapping("/status")
    public ResponseEntity<Response<List<Task>>> getTasksByCompletionStat(
            @RequestParam boolean completed
    ) {
        return ResponseEntity.ok(taskService.getTasksByCompletionStat(completed));
    }

    @GetMapping("/priority")
    public ResponseEntity<Response<List<Task>>> getTasksByPriority(
            @RequestParam String priority
    ) {
        return ResponseEntity.ok(taskService.getTasksByPriority(priority));
    }
}
