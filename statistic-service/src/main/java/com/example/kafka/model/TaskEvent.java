package com.example.kafka.model;

import com.example.model.Priority;
import com.example.model.TaskReport;
import com.example.model.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO for {@link TaskReport}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEvent {
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