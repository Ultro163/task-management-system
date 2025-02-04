package com.example.repository;

import com.example.model.TaskReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskReportRepository extends MongoRepository<TaskReport, Long> {

    @Query("{ 'executorId' : ?0 }")
    List<TaskReport> findAllByExecutorId(long executorId);
}