package com.example.service;

import com.example.dto.task.NewTaskDto;
import com.example.dto.task.ShortTaskDto;
import com.example.dto.task.TaskDto;
import com.example.model.Task;

import java.util.List;

public interface TaskService {
    ShortTaskDto createTask(NewTaskDto newTask);

    ShortTaskDto getTaskById(long id);

    ShortTaskDto getUserTaskById(long userId, long taskId);

    ShortTaskDto updateTaskByUser(long userId, ShortTaskDto task);

    ShortTaskDto updateTaskByAdmin(ShortTaskDto task);

    void deleteTaskByAdmin(long id);

    List<TaskDto> getAuthorTasks(long authorId, int page, int size, String title);

    List<TaskDto> getTasksForExecutor(long executorId, int page, int size, String title);
}