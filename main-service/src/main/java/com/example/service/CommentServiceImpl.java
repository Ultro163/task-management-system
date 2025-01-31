package com.example.service;

import com.example.model.Comment;
import com.example.model.Task;
import com.example.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Сервис для работы с комментариями.
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskService taskServiceImpl;

    /**
     * Создает комментарий исполнителя.
     *
     * @param comment комментарий, который необходимо создать.
     * @return созданный комментарий.
     * @throws AccessDeniedException если автор комментария не является исполнителем задачи.
     */
    @Override
    public Comment createExecutorComment(Comment comment) {
        log.info("Create executor comment {}", comment);
        Task task = taskServiceImpl.getTaskById(comment.getTask().getId());
        if (!Objects.equals(task.getExecutor().getId(), comment.getAuthor().getId())) {
            throw new AccessDeniedException("You can't leave a comment on this task.");
        }
        Comment result = commentRepository.save(comment);
        log.info("Created executor comment {}", comment);
        return result;
    }

    /**
     * Создает комментарий администратора.
     *
     * @param comment комментарий, который необходимо создать.
     * @return созданный комментарий.
     */
    @Override
    public Comment createAdminComment(Comment comment) {
        log.info("Create admin comment {}", comment);
        taskServiceImpl.getTaskById(comment.getTask().getId());
        Comment result = commentRepository.save(comment);
        log.info("Created admin comment {}", comment);
        return result;
    }
}
