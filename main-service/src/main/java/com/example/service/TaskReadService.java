package com.example.service;

import com.example.dto.task.ShortTaskDto;
import com.example.model.Task;

public interface TaskReadService {

    Task getTaskById(long id);
}
