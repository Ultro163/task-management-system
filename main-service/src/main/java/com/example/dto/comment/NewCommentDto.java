package com.example.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO для создания нового комментария.
 * Используется для передачи данных о комментарии, его авторе и задаче.
 *
 * @see com.example.model.Comment
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для создания нового комментария")
public class NewCommentDto {
    @NotNull
    @NotBlank
    @Schema(description = "Текстовое описание комментария", example = "Отличная работа!")
    private String description;

    @NotNull
    @Schema(description = "Идентификатор автора комментария", example = "10")
    private UUID authorId;

    @NotNull
    @Schema(description = "Идентификатор задачи, к которой относится комментарий", example = "20")
    private UUID taskId;
}