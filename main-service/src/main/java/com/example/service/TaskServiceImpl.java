package com.example.service;

import com.example.dto.mappers.TaskMapper;
import com.example.dto.task.NewTaskDto;
import com.example.dto.task.ShortTaskDto;
import com.example.dto.task.TaskDto;
import com.example.error.exception.EntityNotFoundException;
import com.example.kafka.model.TaskEvent;
import com.example.kafka.service.KafkaSender;
import com.example.model.Task;
import com.example.model.TaskState;
import com.example.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для управления задачами.
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private static final String STATISTIC_TOPIC = "STATISTIC-TOPIC";
    private final TaskRepository taskRepository;
    private final UserService userServiceImpl;
    private final TaskReadService taskReadServiceImpl;
    private final KafkaSender kafkaSender;
    private final TaskMapper taskMapper;

    /**
     * Создает новую задачу.
     *
     * @param newTask задача для создания.
     * @return созданная задача.
     */
    @Override
    public ShortTaskDto createTask(NewTaskDto newTask) {
        log.info("Create task: {}", newTask);
        userServiceImpl.getById(newTask.getExecutorId());
        Task task = taskMapper.toEntity(newTask);
        task.setCreatedAt(OffsetDateTime.now());
        Task result = taskRepository.save(task);

        TaskEvent taskEvent = taskMapper.toTaskEvent(task);
        taskEvent.setTaskId(result.getId());
        kafkaSender.sendNewTaskEvent(STATISTIC_TOPIC, taskEvent);
        log.info("Task created: {}", result);
        return taskMapper.toShortTaskDto(result);
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
    @Cacheable(value = "tasks", key = "#id")
    public ShortTaskDto getTaskById(UUID id) {
        log.info("Getting task with ID={}", id);
        return taskMapper.toShortTaskDto(taskRepository.findByIdForAdmin(id).orElseThrow(() -> {
            log.warn("Task not found with ID {}", id);
            return new EntityNotFoundException("Task with ID=" + id + " not found");
        }));
    }

    /**
     * Получает задачу пользователя по ID задачи и ID автора.
     *
     * @param userId ID пользователя.
     * @param taskId ID задачи.
     * @return найденная задача.
     * @throws AccessDeniedException если пользователь не является автором задачи.
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "tasks", key = "#taskId")
    public ShortTaskDto getUserTaskById(UUID userId, UUID taskId) {
        Task task = taskReadServiceImpl.getTaskById(taskId);
        if (task.getAuthor().getId() != userId) {
            throw new AccessDeniedException("You do not have permission to access this task");
        }
        return taskMapper.toShortTaskDto(task);
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
    @CacheEvict(value = "tasks", key = "#task.id")
    public ShortTaskDto updateTaskByUser(UUID userId, ShortTaskDto task) {
        log.info("Update task by user: {}", task);
        Task saveTask = taskReadServiceImpl.getTaskById(task.getId());
        if (task.getExecutorId() == userId) {
            Optional.ofNullable(task.getState()).ifPresent(saveTask::setState);
            Task result = taskRepository.save(saveTask);
            sendUpdateTask(result);
            log.info("Updated task by executor: {}", result);
            return taskMapper.toShortTaskDto(result);
        } else {
            if (saveTask.getAuthor().getId() != userId) {
                log.warn("User with ID={} do not have permission to update this task", userId);
                throw new AccessDeniedException("You do not have permission to update this task");
            }
            Task result = updateFullTask(task, saveTask);
            return taskMapper.toShortTaskDto(result);
        }
    }

    /**
     * Обновляет задачу от имени администратора.
     *
     * @param task обновленная задача.
     * @return обновленная задача.
     */
    @Override
    @CacheEvict(value = "tasks", key = "#task.id")
    public ShortTaskDto updateTaskByAdmin(ShortTaskDto task) {
        log.info("Update task by admin: {}", task);
        Task saveTask = taskReadServiceImpl.getTaskById(task.getId());
        return taskMapper.toShortTaskDto(updateFullTask(task, saveTask));
    }

    /**
     * Полное обновление задачи.
     *
     * @param task     новая информация о задаче.
     * @param saveTask существующая задача.
     * @return обновленная задача.
     */
    private Task updateFullTask(ShortTaskDto task, Task saveTask) {
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
        if (task.getExecutorId() != null) {
            saveTask.getExecutor().setId(task.getExecutorId());
        }
        Task result = taskRepository.save(saveTask);
        sendUpdateTask(result);
        log.info("Updated task by author: {}", result);
        return result;
    }

    private void sendUpdateTask(Task result) {
        TaskEvent taskEvent = taskMapper.toTaskEvent(result);
        if (result.getState() == TaskState.COMPLETED) {
            taskEvent.setCompletedAt(OffsetDateTime.now());
        }
        taskEvent.setUpdatedAt(OffsetDateTime.now());
        kafkaSender.sendUpdatedTaskEvent(STATISTIC_TOPIC, taskEvent);
    }

    /**
     * Удаляет задачу по ID от имени администратора.
     *
     * @param id идентификатор задачи.
     */
    @Override
    @CacheEvict(value = "tasks", key = "#id")
    public void deleteTaskByAdmin(UUID id) {
        log.info("Delete task with ID={}", id);
        taskRepository.existsById(id);
        taskRepository.deleteById(id);
        kafkaSender.sendDeletedTaskEvent(STATISTIC_TOPIC, id);
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
    @Cacheable(value = "tasks", key = "#authorId + '-' + #title + '-' + #page + '-' + #size")
    public List<TaskDto> getAuthorTasks(UUID authorId, int page, int size, String title) {
        Pageable pageable = PageRequest.of(page, size, Sort.unsorted());
        return taskRepository.findAllByAuthorId(authorId, title, pageable).stream()
                .map(taskMapper::toTaskDto).toList();
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
    public List<TaskDto> getTasksForExecutor(UUID executorId, int page, int size, String title) {
        Pageable pageable = PageRequest.of(page, size, Sort.unsorted());
        return taskRepository.findAllByExecutorId(executorId, title, pageable).stream()
                .map(taskMapper::toTaskDto).toList();
    }
}
