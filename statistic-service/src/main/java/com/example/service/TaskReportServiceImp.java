package com.example.service;

import com.example.dto.TaskReportDto;
import com.example.dto.TaskReportGraphDto;
import com.example.dto.mapper.TaskReportMapper;
import com.example.dto.TaskReportRequest;
import com.example.kafka.model.TaskEvent;
import com.example.model.TaskReport;
import com.example.repository.TaskReportRepository;
import com.example.repository.TaskReportRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskReportServiceImp {
    private final TaskReportRepository taskReportRepository;
    private final TaskReportRepositoryCustom taskReportRepositoryCustom;
    private final TaskReportMapper taskReportMapper;

    public void create(TaskEvent taskEvent) {
        log.info("Create TaskReport for {}", taskEvent);
        TaskReport taskReport = taskReportMapper.toEntity(taskEvent);
        taskReportRepository.save(taskReport);
        log.info("Save TaskReport for {}", taskEvent);
    }

    public List<TaskReportDto> getTaskReports(TaskReportRequest request) {
        log.info("Get TaskReport based on request parameters {}", request);
        List<TaskReport> reports = taskReportRepositoryCustom.findByFilters(request);
        return reports.stream().map(taskReportMapper::toTaskReportDto).toList();
    }

    public List<TaskReportGraphDto> getTaskReportsGraph(UUID executorId) {
        return taskReportRepository.findAllByExecutorId(executorId).stream()
                .map(taskReportMapper::toTaskReportGraphDto).toList();
    }


    public void update(TaskEvent taskEvent) {
        log.info("Update TaskReport for {}", taskEvent);
        TaskReport taskReport = taskReportMapper.toEntity(taskEvent);
        taskReportRepository.save(taskReport);
        log.info("Updated TaskReport for {}", taskEvent);
    }

    public void delete(UUID taskId) {
        log.info("Delete TaskReport for {}", taskId);
        taskReportRepository.deleteById(taskId);
        log.info("Deleted TaskReport for {}", taskId);
    }
}
