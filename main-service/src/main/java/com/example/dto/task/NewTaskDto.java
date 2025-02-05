package com.example.dto.task;

import com.example.model.Priority;
import com.example.model.TaskState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO (Data Transfer Object) для создания новой задачи.
 * Используется для передачи данных от клиента на сервер при создании задачи.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для создания новой задачи.")
public class NewTaskDto {

    @NotNull
    @NotBlank
    @Schema(description = "Заголовок задачи", example = "Завершить домашку")
    private String title;

    @NotNull
    @NotBlank
    @Schema(description = "Описание задачи", example = "Необходимо завершить домашнее задание по математике до завтра")
    private String description;

    @NotNull
    @Schema(description = "Текущее состояние задачи",
            example = "IN_PROGRESS",
            allowableValues = {"PENDING", "IN_PROGRESS", "COMPLETED"})
    private TaskState state;

    @NotNull
    @Schema(description = "Приоритет задачи", example = "HIGH", allowableValues = {"LOW", "MEDIUM", "HIGH"})
    private Priority priority;

    @Schema(description = "Идентификатор автора задачи", example = "12")
    private UUID authorId;

    @Schema(description = "Идентификатор исполнителя задачи", example = "4")
    private UUID executorId;
}
