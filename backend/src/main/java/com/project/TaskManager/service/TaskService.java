package com.project.TaskManager.service;

import com.project.TaskManager.dto.Response;
import com.project.TaskManager.dto.TaskRequest;
import com.project.TaskManager.entity.Task;

import java.util.List;

public interface TaskService
{
    Response<Task> createTask(TaskRequest taskRequest);

    Response<List<Task>> getAllUserTasks();

    Response<Task> getTaskById(int id);

    Response<Task> updateTask(TaskRequest taskRequest);

    Response<Void> deleteTask(int id);

    Response<List<Task>> getTasksByCompletionStat(boolean completionStat);

    Response<List<Task>> getTasksByPriority(String priority);
}
