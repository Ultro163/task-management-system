package com.example.controller;

import com.example.dto.comment.CommentDto;
import com.example.dto.comment.NewCommentDto;
import com.example.dto.TaskMapper;
import com.example.model.Comment;
import com.example.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Tag(name = "Comments", description = "Эндпоинты для работы с комментариями")
public class CommentController {
    private final CommentService commentServiceImpl;
    private final TaskMapper taskMapper;

    @PostMapping("/user")
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Добавить комментарий пользователя",
            description = "Создает новый комментарий от имени пользователя. Требуется роль `ROLE_USER`.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    public CommentDto createExecutorComment(@RequestBody @Valid NewCommentDto dto) {
        Comment comment = taskMapper.toEntity(dto);
        return taskMapper.toCommentDto(commentServiceImpl.createExecutorComment(comment));
    }

    @PostMapping("/admin")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Добавить комментарий администратора",
            description = "Создает новый комментарий от имени администратора. Требуется роль `ROLE_ADMIN`.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    public CommentDto createAdminComment(@RequestBody @Valid NewCommentDto dto) {
        Comment comment = taskMapper.toEntity(dto);
        return taskMapper.toCommentDto(commentServiceImpl.createAdminComment(comment));
    }
}