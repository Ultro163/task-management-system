package com.example.service;

import com.example.error.exception.EntityNotFoundException;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask_success() {
        Task task = new Task();
        task.setId(1L);
        User executor = new User();
        executor.setId(2L);
        task.setExecutor(executor);

        when(userService.getById(2L)).thenReturn(executor);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userService, times(1)).getById(2L);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void getTaskById_taskExists() {
        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void getTaskById_taskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> taskService.getTaskById(1L));
        assertEquals("Task with ID=1 not found", exception.getMessage());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void getUserTaskById_userHasAccess() {
        Task task = new Task();
        task.setId(1L);
        User author = new User();
        author.setId(3L);
        task.setAuthor(author);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getUserTaskById(3L, 1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void getUserTaskById_accessDenied() {
        Task task = new Task();
        task.setId(1L);
        User author = new User();
        author.setId(3L);
        task.setAuthor(author);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        assertThrows(AccessDeniedException.class, () -> taskService.getUserTaskById(4L, 1L));
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void updateTaskByAdmin_success() {
        Task task = new Task();
        task.setId(1L);
        Task existingTask = new Task();
        existingTask.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(existingTask);

        Task result = taskService.updateTaskByAdmin(task);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(existingTask);
    }

    @Test
    void deleteTaskByAdmin_success() {
        when(taskRepository.existsById(1L)).thenReturn(true);

        taskService.deleteTaskByAdmin(1L);

        verify(taskRepository, times(1)).existsById(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAuthorTasks_success() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findAllByAuthorId(eq(3L), any(), any())).thenReturn(List.of(task));

        List<Task> tasks = taskService.getAuthorTasks(3L, 0, 10, null);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals(1L, tasks.get(0).getId());
        verify(taskRepository, times(1)).findAllByAuthorId(eq(3L), any(), any());
    }

    @Test
    void getTasksForExecutor_success() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepository.findAllByExecutorId(eq(2L), any(), any())).thenReturn(List.of(task));

        List<Task> tasks = taskService.getTasksForExecutor(2L, 0, 10, null);

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals(1L, tasks.get(0).getId());
        verify(taskRepository, times(1)).findAllByExecutorId(eq(2L), any(), any());
    }
}
