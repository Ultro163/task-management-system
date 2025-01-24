package com.example.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) для создания нового пользователя.
 * Это DTO используется для передачи данных при создании нового пользователя, включая его имя, электронную почту и пароль.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для создания нового пользователя.")
public class NewUserDto {

    @Schema(description = "Имя пользователя", example = "Иван Иванов", minLength = 2)
    @NotNull
    @Size(min = 2)
    @NotBlank
    private String name;

    @Schema(description = "Электронная почта пользователя", example = "ivan.ivanov@example.com", minLength = 6)
    @NotNull
    @Email
    @Size(min = 6)
    @NotBlank
    private String email;

    @Schema(description = "Пароль пользователя", example = "password123", minLength = 6)
    @NotNull
    @NotBlank
    private String password;
}
