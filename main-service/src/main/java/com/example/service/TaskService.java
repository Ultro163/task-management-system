package com.example.service;

import com.example.dto.task.NewTaskDto;
import com.example.dto.task.ShortTaskDto;
import com.example.dto.task.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    ShortTaskDto createTask(NewTaskDto newTask);

    ShortTaskDto getTaskById(UUID id);

    ShortTaskDto getUserTaskById(UUID userId, UUID taskId);

    ShortTaskDto updateTaskByUser(UUID userId, ShortTaskDto task);

    ShortTaskDto updateTaskByAdmin(ShortTaskDto task);

    void deleteTaskByAdmin(UUID id);

    List<TaskDto> getAuthorTasks(UUID authorId, int page, int size, String title);

    List<TaskDto> getTasksForExecutor(UUID executorId, int page, int size, String title);
}