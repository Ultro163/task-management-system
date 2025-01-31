package com.example.service;

import com.example.model.Comment;

public interface CommentService {
    Comment createExecutorComment(Comment comment);

    Comment createAdminComment(Comment comment);
}