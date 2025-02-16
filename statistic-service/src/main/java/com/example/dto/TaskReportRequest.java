package com.example.dto;

import com.example.model.Priority;
import com.example.model.TaskState;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskReportRequest {
    private UUID executorId;
    private UUID authorId;
    private String title;
    private Priority priority;
    private TaskState taskState;
    private int page;
    private int size;
    private String sortDirection;
}