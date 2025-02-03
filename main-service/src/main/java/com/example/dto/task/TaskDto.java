package com.example.dto.task;

import com.example.dto.comment.CommentDto;
import com.example.dto.user.UserDto;
import com.example.model.Priority;
import com.example.model.TaskState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO (Data Transfer Object) для представления полной информации о задаче.
 * Это DTO используется для передачи всех данных о задаче, включая автора, исполнителя и комментарии.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для представления полной информации о задаче. Включает все данные о задаче, авторе, исполнителе и комментариях.")
public class TaskDto {

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

    @Schema(description = "Информация о авторе задачи", implementation = UserDto.class)
    private UserDto author;

    @Schema(description = "Информация об исполнителе задачи", implementation = UserDto.class)
    private UserDto executor;

    private LocalDateTime createdAt;

    @Schema(description = "Список комментариев к задаче", implementation = CommentDto.class)
    private List<CommentDto> comments = new ArrayList<>();
}
