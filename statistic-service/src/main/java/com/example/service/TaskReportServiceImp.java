package com.example.service;

import com.example.dto.TaskReportMapper;
import com.example.kafka.model.TaskEvent;
import com.example.model.TaskReport;
import com.example.repository.TaskReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskReportServiceImp {
    private final TaskReportRepository taskReportRepository;
    private final TaskReportMapper taskReportMapper;

    public void create(TaskEvent taskEvent) {
        TaskReport taskReport = taskReportMapper.toEntity(taskEvent);
        taskReportRepository.save(taskReport);
    }

    public List<TaskReport> getTaskReport() {
        return taskReportRepository.findAll();
    }

    public void update(TaskEvent taskEvent) {
        TaskReport taskReport = taskReportMapper.toEntity(taskEvent);
        taskReportRepository.save(taskReport);
    }
}
