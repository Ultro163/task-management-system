package com.example.repository;

import com.example.model.TaskReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskReportRepository extends MongoRepository<TaskReport, Long> {
}