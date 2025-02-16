package com.example.service;

import com.example.dto.mappers.TaskMapper;
import com.example.dto.task.NewTaskDto;
import com.example.dto.task.ShortTaskDto;
import com.example.dto.task.TaskDto;
import com.example.error.exception.EntityNotFoundException;
import com.example.kafka.model.TaskEvent;
import com.example.kafka.service.KafkaSender;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @Mock
    private TaskReadService taskReadService;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private KafkaSender kafkaSender;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void createTask_success() {
        UUID executorId = UUID.randomUUID();
        User executor = new User();
        executor.setId(executorId);

        NewTaskDto newTaskDto = new NewTaskDto();
        newTaskDto.setExecutorId(executorId);

        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setExecutor(executor);

        ShortTaskDto shortTaskDto = new ShortTaskDto();
        shortTaskDto.setId(task.getId());

        TaskEvent taskEvent = new TaskEvent();
        taskEvent.setTaskId(task.getId());

        when(userService.getById(executorId)).thenReturn(executor);
        when(taskMapper.toEntity(newTaskDto)).thenReturn(task);
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskMapper.toShortTaskDto(task)).thenReturn(shortTaskDto);
        when(taskMapper.toTaskEvent(task)).thenReturn(taskEvent);

        ShortTaskDto result = taskService.createTask(newTaskDto);

        assertNotNull(result);
        assertEquals(task.getId(), result.getId());

        verify(userService, times(1)).getById(executorId);
        verify(taskMapper, times(1)).toEntity(newTaskDto);
        verify(taskRepository, times(1)).save(any(Task.class));
        verify(taskMapper, times(1)).toShortTaskDto(task);
        verify(taskMapper, times(1)).toTaskEvent(task);
        verify(kafkaSender, times(1))
                .sendNewTaskEvent(eq("STATISTIC-TOPIC"), any(TaskEvent.class));
    }

    @Test
    void getTaskById_taskExists() {
        UUID taskId = UUID.randomUUID();
        Task task = new Task();
        task.setId(taskId);
        ShortTaskDto shortTaskDto = new ShortTaskDto();
        shortTaskDto.setId(taskId);

        when(taskRepository.findByIdForAdmin(taskId)).thenReturn(Optional.of(task));
        when(taskMapper.toShortTaskDto(task)).thenReturn(shortTaskDto);

        ShortTaskDto result = taskService.getTaskById(taskId);

        assertNotNull(result);
        assertEquals(taskId, result.getId());
        verify(taskRepository, times(1)).findByIdForAdmin(taskId);
    }

    @Test
    void getTaskById_taskNotFound() {
        UUID taskId = UUID.randomUUID();

        when(taskRepository.findByIdForAdmin(taskId)).thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(EntityNotFoundException.class, () -> taskService.getTaskById(taskId));

        assertEquals("Task with ID=" + taskId + " not found", exception.getMessage());
        verify(taskRepository, times(1)).findByIdForAdmin(taskId);
    }

    @Test
    void getUserTaskById_userHasAccess() {
        UUID userId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        Task task = new Task();
        task.setId(taskId);
        User author = new User();
        author.setId(userId);
        task.setAuthor(author);

        when(taskReadService.getTaskById(taskId)).thenReturn(task);
        when(taskMapper.toShortTaskDto(task)).thenReturn(new ShortTaskDto());

        ShortTaskDto result = taskService.getUserTaskById(userId, taskId);

        assertNotNull(result);
        verify(taskReadService, times(1)).getTaskById(taskId);
    }

    @Test
    void getUserTaskById_accessDenied() {
        UUID userId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        Task task = new Task();
        task.setId(taskId);
        User author = new User();
        author.setId(UUID.randomUUID());
        task.setAuthor(author);

        when(taskReadService.getTaskById(taskId)).thenReturn(task);

        assertThrows(AccessDeniedException.class, () -> taskService.getUserTaskById(userId, taskId));
        verify(taskReadService, times(1)).getTaskById(taskId);
    }

    @Test
    void deleteTaskByAdmin_success() {
        UUID taskId = UUID.randomUUID();

        taskService.deleteTaskByAdmin(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
        verify(kafkaSender, times(1)).sendDeletedTaskEvent("STATISTIC-TOPIC", taskId);
    }

    @Test
    void getAuthorTasks_success() {
        UUID authorId = UUID.randomUUID();
        Task task = new Task();
        task.setId(UUID.randomUUID());

        when(taskRepository.findAllByAuthorId(eq(authorId), any(), any()))
                .thenReturn(List.of(task));
        when(taskMapper.toTaskDto(task)).thenReturn(new TaskDto());

        List<TaskDto> tasks = taskService.getAuthorTasks(authorId, 0, 10, null);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        verify(taskRepository, times(1)).findAllByAuthorId(eq(authorId), any(), any());
    }

    @Test
    void getTasksForExecutor_success() {
        UUID executorId = UUID.randomUUID();
        Task task = new Task();
        task.setId(UUID.randomUUID());

        when(taskRepository.findAllByExecutorId(eq(executorId), any(), any()))
                .thenReturn(List.of(task));
        when(taskMapper.toTaskDto(task)).thenReturn(new TaskDto());

        List<TaskDto> tasks = taskService.getTasksForExecutor(executorId, 0, 10, null);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        verify(taskRepository, times(1)).findAllByExecutorId(eq(executorId), any(), any());
    }
}
