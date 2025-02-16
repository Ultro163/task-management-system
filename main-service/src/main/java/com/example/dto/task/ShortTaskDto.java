package com.example.dto.task;

import com.example.model.Priority;
import com.example.model.TaskState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO (Data Transfer Object) для представления краткой информации о задаче.
 * Это DTO используется для передачи базовой информации о задаче.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для представления краткой информации о задаче. Включает основные данные, такие как заголовок, описание, состояние и приоритет задачи.")
public class ShortTaskDto {

    @Schema(description = "Уникальный идентификатор задачи", example = "1")
    private UUID id;

    @Schema(description = "Заголовок задачи", example = "Завершить домашку")
    private String title;

    @Schema(description = "Краткое описание задачи", example = "Необходимо завершить домашку по математике")
    private String description;

    @Schema(description = "Текущее состояние задачи",
            example = "IN_PROGRESS",
            allowableValues = {"PENDING", "IN_PROGRESS", "COMPLETED"})
    private TaskState state;

    @Schema(description = "Приоритет задачи", example = "HIGH", allowableValues = {"LOW", "MEDIUM", "HIGH"})
    private Priority priority;

    @Schema(description = "Идентификатор автора задачи", example = "12")
    private UUID authorId;

    @Schema(description = "Идентификатор исполнителя задачи", example = "4")
    private UUID executorId;

    private OffsetDateTime createdAt;
}