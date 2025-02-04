package com.example.controller;

import com.example.dto.TaskReportDto;
import com.example.dto.TaskReportMapper;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic/task")
public class TaskReportController {
    private final TaskReportServiceImp taskReportServiceImp;
    private final TaskReportMapper taskReportMapper;

    @GetMapping
    public List<TaskReportDto> getTaskReport(@RequestParam(required = false) Long executorId,
                                             @RequestParam(required = false) Long authorId,
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