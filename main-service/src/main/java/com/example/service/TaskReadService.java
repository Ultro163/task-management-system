package com.example.service;

import com.example.model.Task;

import java.util.UUID;

public interface TaskReadService {

    Task getTaskById(UUID id);
}
