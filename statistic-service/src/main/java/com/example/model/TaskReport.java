package com.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Document
public class TaskReport {

    @Id
    private UUID id;
    private UUID authorId;
    private UUID executorId;
    private String title;
    @Field(targetType = FieldType.DATE)
    private OffsetDateTime createdAt;
    @Field(targetType = FieldType.DATE)
    private OffsetDateTime updatedAt;
    @Field(targetType = FieldType.DATE)
    private OffsetDateTime completedAt;
    private Priority priority;
    private TaskState state;
}