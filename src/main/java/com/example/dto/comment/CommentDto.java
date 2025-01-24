package com.example.dto.comment;

import com.example.dto.task.TaskDtoForComment;
import com.example.dto.user.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для представления комментария.
 * Содержит информацию о комментарии, его авторе и задаче, к которой он относится.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для представления комментария")
public class CommentDto {

    @Schema(description = "Уникальный идентификатор комментария", example = "1")
    private Long id;

    @Schema(description = "Текстовое описание комментария", example = "Отличная работа!")
    private String description;

    @Schema(description = "Информация об авторе комментария")
    private UserDto author;

    @Schema(description = "Информация о задаче, к которой относится комментарий")
    private TaskDtoForComment task;
}
