package com.example.controller;

import com.example.dto.mappers.UserMapper;
import com.example.dto.jwt.JwtAuthenticationResponse;
import com.example.dto.user.NewUserDto;
import com.example.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Эндпоинты для регистрации и аутентификации пользователей")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/sign-up")
    @Operation(
            summary = "Регистрация нового пользователя",
            description = "Создает нового пользователя и возвращает JWT-токен для последующих запросов"
    )
    public JwtAuthenticationResponse signUp(@RequestBody @Valid NewUserDto request) {
        return authenticationService.signUp(userMapper.toEntity(request));
    }

    @PostMapping("/sign-in")
    @Operation(
            summary = "Аутентификация пользователя",
            description = "Проверяет учетные данные пользователя и возвращает JWT-токен при успешной аутентификации"
    )
    public JwtAuthenticationResponse signIn(@RequestBody @Valid NewUserDto request) {
        return authenticationService.signIn(userMapper.toEntity(request));
    }
}