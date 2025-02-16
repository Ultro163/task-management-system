package com.example.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для ответа при аутентификации с использованием JWT.
 * Содержит токен, который будет использоваться для авторизации в системе.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для ответа при аутентификации с использованием JWT")
public class JwtAuthenticationResponse {
    @Schema(description = "JWT токен для авторизации", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
}