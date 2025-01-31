package com.example.service;

import com.example.error.exception.EntityNotFoundException;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для управления задачами.
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userServiceImpl;

    /**
     * Создает новую задачу.
     *
     * @param task задача для создания.
     * @return созданная задача.
     */
    @Override
    public Task createTask(Task task) {
        log.info("Create task: {}", task);
        userServiceImpl.getById(task.getExecutor().getId());
        Task result = taskRepository.save(task);
        log.info("Task created: {}", result);
        return result;
    }

    /**
     * Получает задачу по ID.
     *
     * @param id идентификатор задачи.
     * @return найденная задача.
     * @throws EntityNotFoundException если задача не найдена.
     */
    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(long id) {
        log.info("Getting task with ID={}", id);
        return taskRepository.findById(id).orElseThrow(() -> {
            log.warn("Task not found with ID {}", id);
            return new EntityNotFoundException("Task with ID=" + id + " not found");
        });
    }

    /**
     * Получает задачу пользователя по ID задачи и ID автора.
     *
     * @param userId  ID пользователя.
     * @param taskId  ID задачи.
     * @return найденная задача.
     * @throws AccessDeniedException если пользователь не является автором задачи.
     */
    @Override
    @Transactional(readOnly = true)
    public Task getUserTaskById(long userId, long taskId) {
        Task task = getTaskById(taskId);
        if (task.getAuthor().getId() != userId) {
            throw new AccessDeniedException("You do not have permission to access this task");
        }
        return task;
    }

    /**
     * Обновляет задачу от имени пользователя.
     *
     * @param userId ID пользователя, выполняющего обновление.
     * @param task   обновленная задача.
     * @return обновленная задача.
     * @throws AccessDeniedException если пользователь не имеет прав на обновление.
     */
    @Override
    public Task updateTaskByUser(long userId, Task task) {
        log.info("Update task by user: {}", task);
        Task saveTask = getTaskById(task.getId());
        if (task.getExecutor().getId() == userId) {
            Optional.ofNullable(task.getState()).ifPresent(saveTask::setState);
            Task result = taskRepository.save(saveTask);
            log.info("Updated task by executor: {}", result);
            return result;
        } else {
            if (task.getAuthor().getId() == userId) {
                log.warn("User with ID={} do not have permission to update this task", userId);
                throw new AccessDeniedException("You do not have permission to update this task");
            }
            return updateFullTask(task, saveTask);
        }
    }

    /**
     * Обновляет задачу от имени администратора.
     *
     * @param task обновленная задача.
     * @return обновленная задача.
     */
    @Override
    public Task updateTaskByAdmin(Task task) {
        log.info("Update task by admin: {}", task);
        Task saveTask = getTaskById(task.getId());
        return updateFullTask(task, saveTask);
    }

    /**
     * Полное обновление задачи.
     *
     * @param task     новая информация о задаче.
     * @param saveTask существующая задача.
     * @return обновленная задача.
     */
    private Task updateFullTask(Task task, Task saveTask) {
        if (task.getTitle() != null) {
            saveTask.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            saveTask.setDescription(task.getDescription());
        }
        if (task.getState() != null) {
            saveTask.setState(task.getState());
        }
        if (task.getPriority() != null) {
            saveTask.setPriority(task.getPriority());
        }
        if (task.getExecutor() != null) {
            saveTask.setExecutor(task.getExecutor());
        }
        Task result = taskRepository.save(saveTask);
        log.info("Updated task by author: {}", result);
        return result;
    }

    /**
     * Удаляет задачу по ID от имени администратора.
     *
     * @param id идентификатор задачи.
     */
    @Override
    public void deleteTaskByAdmin(long id) {
        log.info("Delete task with ID={}", id);
        taskRepository.existsById(id);
        taskRepository.deleteById(id);
        log.info("Deleted task with ID={}", id);
    }

    /**
     * Получает список задач автора с фильтрацией по названию и пагинацией.
     *
     * @param authorId ID автора.
     * @param page     номер страницы.
     * @param size     размер страницы.
     * @param title    название задачи для фильтрации.
     * @return список задач автора.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Task> getAuthorTasks(long authorId, int page, int size, String title) {
        Pageable pageable = PageRequest.of(page, size, Sort.unsorted());
        return taskRepository.findAllByAuthorId(authorId, title, pageable);
    }

    /**
     * Получает список задач исполнителя с фильтрацией по названию и пагинацией.
     *
     * @param executorId ID исполнителя.
     * @param page       номер страницы.
     * @param size       размер страницы.
     * @param title      название задачи для фильтрации.
     * @return список задач исполнителя.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksForExecutor(long executorId, int page, int size, String title) {
        Pageable pageable = PageRequest.of(page, size, Sort.unsorted());
        return taskRepository.findAllByExecutorId(executorId, title, pageable);
    }
}
