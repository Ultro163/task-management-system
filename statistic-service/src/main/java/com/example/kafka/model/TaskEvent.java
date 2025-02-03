package com.example.kafka.model;

import com.example.model.Priority;
import com.example.model.TaskReport;
import com.example.model.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for {@link TaskReport}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEvent {
    private Long taskId;
    private Long authorId;
    private Long executorId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
    private Priority priority;
    private TaskState status;
}