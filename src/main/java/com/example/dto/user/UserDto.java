package com.example.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) для представления информации о пользователе.
 * Это DTO используется для передачи данных о пользователе, включая его идентификатор, имя и электронную почту.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для представления информации о пользователе.")
public class UserDto {

    @Schema(description = "Уникальный идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "Имя пользователя", example = "Иван Иванов")
    private String name;

    @Schema(description = "Электронная почта пользователя", example = "ivan.ivanov@example.com")
    private String email;
}