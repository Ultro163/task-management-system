package com.example.controller;

import com.example.dto.TaskReportDto;
import com.example.dto.TaskReportRequest;
import com.example.model.Priority;
import com.example.model.TaskState;
import com.example.service.TaskReportServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic/task")
public class TaskReportController {
    private final TaskReportServiceImp taskReportServiceImp;

    @GetMapping
    public List<TaskReportDto> getTaskReport(@RequestParam(required = false) UUID executorId,
                                             @RequestParam(required = false) UUID authorId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(required = false) String title,
                                             @RequestParam(required = false) Priority priority,
                                             @RequestParam(required = false) TaskState taskState,
                                             @RequestParam(defaultValue = "asc") String sortDirection) {
        TaskReportRequest request = new TaskReportRequest();
        request.setExecutorId(executorId);
        request.setAuthorId(authorId);
        request.setTitle(title);
        request.setPriority(priority);
        request.setTaskState(taskState);
        request.setPage(page);
        request.setSize(size);
        request.setSortDirection(sortDirection);

        return taskReportServiceImp.getTaskReports(request);
    }
}