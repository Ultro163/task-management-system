package com.example.controller;

import com.example.dto.TaskReportDto;
import com.example.dto.TaskReportGraphDto;
import com.example.dto.mapper.TaskReportMapper;
import com.example.service.TaskReportServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class TaskReportGraphQLController {
    private final TaskReportServiceImp taskReportServiceImp;
    private final TaskReportMapper taskReportMapper;

    @QueryMapping
    public List<TaskReportGraphDto> getTaskReports(@Argument UUID executorId) {
        return taskReportServiceImp.getTaskReportsGraph(executorId);
    }

//    @MutationMapping
//    public void deleteTaskReport(@Argument UUID id) {
//        taskReportServiceImp.delete(id);
//    }
}
