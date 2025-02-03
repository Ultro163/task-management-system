package com.example.kafka.model;

import com.example.model.Priority;
import com.example.model.TaskState;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TaskEvent implements Serializable {
    private Long taskId;
    private Long authorId;
    private Long executorId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
    private Priority priority;
    private TaskState state;
}