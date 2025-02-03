package com.example.dto;

import com.example.kafka.model.TaskEvent;
import com.example.model.TaskReport;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskReportMapper {
    @Mapping(source = "taskId", target = "id")
    TaskReport toEntity(TaskEvent taskEvent);

    @Mapping(source = "id", target = "taskId")
    TaskEvent toTaskEvent(TaskReport taskReport);

    TaskReport toEntity(TaskReportDto taskReportDto);

    @InheritInverseConfiguration(name = "toEntity")
    TaskReportDto toTaskReportDto(TaskReport taskReport);
}