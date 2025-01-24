package com.example.controller;

import com.example.dto.mappers.TaskMapper;
import com.example.dto.task.NewTaskDto;
import com.example.dto.task.ShortTaskDto;
import com.example.dto.task.TaskDto;
import com.example.model.Task;
import com.example.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/tasks")
@RequiredArgsConstructor
@Secured("ROLE_ADMIN")
@Tag(name = "Managing tasks by the admin", description = "Управление задачами для администратора")
@SecurityRequirement(name = "Bearer Authentication")
public class AdminTaskController {
    private final TaskService taskServiceImpl;
    private final TaskMapper taskMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать задачу", description = "Создает новую задачу на основе переданных данных")

    public TaskDto createTask(@RequestBody NewTaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        return taskMapper.toTaskDto(taskServiceImpl.createTask(task));
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Получить задачу по ID", description = "Возвращает краткую информацию о задаче по её идентификатору")
    public ShortTaskDto getTaskById(@PathVariable long taskId) {
        return taskMapper.toShortTaskDto(taskServiceImpl.getTaskById(taskId));
    }

    @PatchMapping
    @Operation(summary = "Обновить задачу", description = "Обновляет информацию о задаче на основе переданных данных")
    public ShortTaskDto updateTask(@RequestBody ShortTaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        return taskMapper.toShortTaskDto(taskServiceImpl.updateTaskByAdmin(task));
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить задачу", description = "Удаляет задачу по её идентификатору")
    public void deleteTask(@PathVariable long taskId) {
        taskServiceImpl.deleteTaskByAdmin(taskId);
    }

    @GetMapping("/author")
    @Operation(summary = "Получить задачи автора", description = "Возвращает список задач, созданных автором")
    public List<TaskDto> getAuthorTasks(@RequestParam long authorId,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(required = false) String title) {
        return taskServiceImpl.getAuthorTasks(authorId, page, size, title).stream()
                .map(taskMapper::toTaskDto).toList();
    }

    @GetMapping("/executor")
    @Operation(summary = "Получить задачи для исполнителя", description = "Возвращает список задач, назначенных исполнителю")
    public List<TaskDto> getTasksForExecutor(@RequestParam long executorId,
                                             @RequestParam int page,
                                             @RequestParam int size,
                                             @RequestParam(required = false) String title) {
        return taskServiceImpl.getTasksForExecutor(executorId, page, size, title).stream()
                .map(taskMapper::toTaskDto).toList();
    }
}