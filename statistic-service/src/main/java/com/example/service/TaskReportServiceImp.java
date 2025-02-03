package com.example.service;

import com.example.dto.TaskEvent;
import com.example.dto.mappers.TaskReportMapper;
import com.example.model.TaskReport;
import com.example.repository.TaskReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskReportServiceImp {
    private final TaskReportRepository taskReportRepository;
    private final TaskReportMapper taskReportMapper;

    public void create(TaskEvent taskEvent) {
        TaskReport taskReport = taskReportMapper.toEntity(taskEvent);
        taskReportRepository.save(taskReport);
    }
}
