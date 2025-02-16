package com.example.dto;

import com.example.model.Priority;
import com.example.model.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.example.model.TaskReport}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskReportDto {
    private UUID id;
    private UUID authorId;
    private UUID executorId;
    private String title;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime completedAt;
    private Priority priority;
    private TaskState state;
}