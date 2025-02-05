package com.example.dto.mapper;

import com.example.dto.TaskReportDto;
import com.example.dto.TaskReportGraphDto;
import com.example.kafka.model.TaskEvent;
import com.example.model.TaskReport;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
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

//    TaskReport toEntity(TaskReportGraphDto taskReportGraphDto);

//    @Mappings({
//            @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapToOffsetDateTime"),
//            @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "mapToOffsetDateTime"),
//            @Mapping(source = "completedAt", target = "completedAt", qualifiedByName = "mapToOffsetDateTime")
//    })
    TaskReportGraphDto toTaskReportGraphDto(TaskReport taskReport);
}