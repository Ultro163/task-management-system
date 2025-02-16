package com.example.kafka.model;

import com.example.model.Priority;
import com.example.model.TaskState;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class TaskEvent implements Serializable {
    private UUID taskId;
    private UUID authorId;
    private UUID executorId;
    private String title;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime completedAt;
    private Priority priority;
    private TaskState state;
}