package com.example.service;

import com.example.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    Task getTaskById(long id);

    Task getUserTaskById(long userId, long taskId);

    Task updateTaskByUser(long userId, Task task);

    Task updateTaskByAdmin(Task task);

    void deleteTaskByAdmin(long id);

    List<Task> getAuthorTasks(long authorId, int page, int size, String title);

    List<Task> getTasksForExecutor(long executorId, int page, int size, String title);
}