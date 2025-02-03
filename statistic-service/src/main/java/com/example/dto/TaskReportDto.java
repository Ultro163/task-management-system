package com.example.dto;

import com.example.model.Priority;
import com.example.model.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.model.TaskReport}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskReportDto {
    private Long id;
    private Long authorId;
    private Long executorId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
    private Priority priority;
    private TaskState status;
}