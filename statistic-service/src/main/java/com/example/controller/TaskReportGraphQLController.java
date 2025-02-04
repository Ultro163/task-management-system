package com.example.controller;

import com.example.dto.TaskReportDto;
import com.example.dto.TaskReportMapper;
import com.example.service.TaskReportServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskReportGraphQLController {
    private final TaskReportServiceImp taskReportServiceImp;
    private final TaskReportMapper taskReportMapper;

    @QueryMapping
    public List<TaskReportDto> getTaskReports(@Argument String executorId) {
        return taskReportServiceImp.getTaskReportsGraph(Long.parseLong(executorId));
    }

//    @MutationMapping
//    public void deleteTaskReport(@Argument Long id) {
//        taskReportServiceImp.delete(id);
//    }
}
