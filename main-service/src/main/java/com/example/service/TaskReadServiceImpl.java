package com.example.service;

import com.example.error.exception.EntityNotFoundException;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskReadServiceImpl implements TaskReadService {

    private final TaskRepository taskRepository;

    /**
     * Получает задачу по ID.
     *
     * @param id идентификатор задачи.
     * @return найденная задача.
     * @throws EntityNotFoundException если задача не найдена.
     */
    @Transactional(readOnly = true)
    public Task getTaskById(UUID id) {
        log.info("Getting task with ID={}", id);
        return taskRepository.findByIdForAdmin(id).orElseThrow(() -> {
            log.warn("Task not found with ID {}", id);
            return new EntityNotFoundException("Task with ID=" + id + " not found");
        });
    }
}
