package com.project.TaskManager.service;

import com.project.TaskManager.dto.Response;
import com.project.TaskManager.dto.TaskRequest;
import com.project.TaskManager.entity.Task;
import com.project.TaskManager.entity.User;
import com.project.TaskManager.enums.Priority;
import com.project.TaskManager.exceptions.NotFoundException;
import com.project.TaskManager.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService
{
    private final TaskRepo taskRepo;
    private final UserService userService;

    @Override
    @Transactional
    public Response<Task> createTask(TaskRequest taskRequest) {
        User user = userService.getCurrentLoggedInUser();
        Task tempTask = Task.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .priority(taskRequest.getPriority())
                .user(user)
                .completed(taskRequest.getCompleted())
                .build();

        Task savedTask = taskRepo.save(tempTask);

        return Response.<Task>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Task created successfully.")
                .data(savedTask)
                .build();
    }

    @Override
    public Response<List<Task>> getAllUserTasks() {
        User user = userService.getCurrentLoggedInUser();
        List<Task> taskList = taskRepo.findByUser(user, Sort.by(Sort.Direction.DESC,"id"));

        return Response.<List<Task>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Task retrieved successfully.")
                .data(taskList)
                .build();
    }

    @Override
    public Response<Task> getTaskById(int id) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new NotFoundException("Task not found."));

        return Response.<Task>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Task found.")
                .data(task)
                .build();
    }

    @Override
    @Transactional
    public Response<Task> updateTask(TaskRequest taskRequest) {
        Task taskToUpdate = taskRepo.findById(taskRequest.getId()).orElseThrow(() -> new NotFoundException("Task not found."));
        if (taskRequest.getTitle() != null) taskToUpdate.setTitle(taskRequest.getTitle());
        if (taskRequest.getDescription() != null) taskToUpdate.setDescription(taskRequest.getDescription());
        if (taskRequest.getCompleted() != null) taskToUpdate.setCompleted(taskRequest.getCompleted());
        if (taskRequest.getPriority() != null) taskToUpdate.setPriority(taskRequest.getPriority());
        if (taskRequest.getDueDate() != null) taskToUpdate.setDueDate(taskRequest.getDueDate());
        taskToUpdate.setUpdatedAt(LocalDateTime.now());

        Task updatedTask =  taskRepo.save(taskToUpdate);

        return Response.<Task>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Task updated successfully")
                .data(updatedTask)
                .build();
    }

    @Override
    @Transactional
    public Response<Void> deleteTask(int id) {
        if(!taskRepo.existsById(id))
        {
            throw new NotFoundException("Task could not be found.");
        }

        taskRepo.deleteById(id);

        return Response.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Task deleted successfully")
                .build();
    }

    @Override
    public Response<List<Task>> getTasksByCompletionStat(boolean completionStat) {
        User user = userService.getCurrentLoggedInUser();
        List<Task> taskList = taskRepo.findByCompletedAndUser(completionStat,user);

        return Response.<List<Task>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("filtered tasks fetched successfully.")
                .data(taskList)
                .build();
    }

    @Override
    public Response<List<Task>> getTasksByPriority(String priority) {
        User user = userService.getCurrentLoggedInUser();
        Priority pr = Priority.valueOf(priority.toUpperCase());
        List<Task> taskList = taskRepo.findByPriorityAndUser(pr,user,Sort.by(Sort.Direction.DESC,"id"));


        return Response.<List<Task>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Filtered tasks by priority")
                .data(taskList)
                .build();
    }
}
