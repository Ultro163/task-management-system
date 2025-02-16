package com.example.dto.mappers;

import com.example.dto.comment.CommentDto;
import com.example.dto.comment.NewCommentDto;
import com.example.dto.task.NewTaskDto;
import com.example.dto.task.ShortTaskDto;
import com.example.dto.task.TaskDto;
import com.example.kafka.model.TaskEvent;
import com.example.model.Comment;
import com.example.model.Task;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class, UserMapper.class, TaskMapper.class})
public interface TaskMapper {
    @InheritInverseConfiguration(name = "toEntity")
    TaskDto toTaskDto(Task task);

    @Mapping(source = "executorId", target = "executor.id")
    @Mapping(source = "authorId", target = "author.id")
    Task toEntity(NewTaskDto newTaskDto);

    @InheritInverseConfiguration(name = "toEntity")
    CommentDto toCommentDto(Comment comment);

    @InheritInverseConfiguration(name = "toEntity")
    ShortTaskDto toShortTaskDto(Task task);

    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "authorId", target = "author.id")
    Comment toEntity(NewCommentDto newCommentDto);

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "executor.id", target = "executorId")
    @Mapping(source = "author.id", target = "authorId")
    TaskEvent toTaskEvent(Task task);
}