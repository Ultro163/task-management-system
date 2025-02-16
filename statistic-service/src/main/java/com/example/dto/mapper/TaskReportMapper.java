package com.example.dto.mapper;

import com.example.dto.TaskReportDto;
import com.example.dto.TaskReportGraphDto;
import com.example.kafka.model.TaskEvent;
import com.example.model.TaskReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = TimeMapper.class)
public interface TaskReportMapper {
    @Mapping(source = "taskId", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapToLocalDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "mapToLocalDateTime")
    @Mapping(source = "completedAt", target = "completedAt", qualifiedByName = "mapToLocalDateTime")
    TaskReport toEntity(TaskEvent taskEvent);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapToOffsetDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "mapToOffsetDateTime")
    @Mapping(source = "completedAt", target = "completedAt", qualifiedByName = "mapToOffsetDateTime")
    TaskReportDto toTaskReportDto(TaskReport taskReport);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapToOffsetDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "mapToOffsetDateTime")
    @Mapping(source = "completedAt", target = "completedAt", qualifiedByName = "mapToOffsetDateTime")
    TaskReportGraphDto toTaskReportGraphDto(TaskReport taskReport);
}