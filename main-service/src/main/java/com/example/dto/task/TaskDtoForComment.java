package com.example.dto.task;

import com.example.model.Priority;
import com.example.model.TaskState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) для представления информации о задаче, предназначенной для комментариев.
 * Это DTO используется для передачи основных данных о задаче, таких как заголовок, описание, состояние и приоритет.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для комментариев. Включает основные данные о задаче.")
public class TaskDtoForComment {

    @Schema(description = "Уникальный идентификатор задачи", example = "1")
    private Long id;

    @Schema(description = "Заголовок задачи", example = "Завершить домашку")
    private String title;

    @Schema(description = "Полное описание задачи", example = "Необходимо завершить домашку по математике до завтра")
    private String description;

    @Schema(description = "Текущее состояние задачи",
            example = "IN_PROGRESS",
            allowableValues = {"PENDING", "IN_PROGRESS", "COMPLETED"})
    private TaskState state;

    @Schema(description = "Приоритет задачи", example = "HIGH", allowableValues = {"LOW", "MEDIUM", "HIGH"})
    private Priority priority;

    @Schema(description = "Идентификатор автора задачи", example = "123")
    private Long authorId;

    @Schema(description = "Идентификатор исполнителя задачи", example = "456")
    private Long executorId;

    private LocalDateTime createdAt;
}
