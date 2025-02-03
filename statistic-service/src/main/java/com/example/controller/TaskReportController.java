package com.example.controller;

import com.example.dto.TaskReportDto;
import com.example.dto.TaskReportMapper;
import com.example.service.TaskReportServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic/task")
public class TaskReportController {
    private final TaskReportServiceImp taskReportServiceImp;
    private final TaskReportMapper taskReportMapper;

    @GetMapping
    public List<TaskReportDto> getTaskReport() {
        return taskReportServiceImp.getTaskReport().stream().map(taskReportMapper::toTaskReportDto).toList();
    }
}