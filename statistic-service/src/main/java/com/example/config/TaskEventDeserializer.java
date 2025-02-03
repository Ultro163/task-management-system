package com.example.config;

import com.example.dto.TaskEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;  // Для поддержки LocalDateTime
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class TaskEventDeserializer implements Deserializer<TaskEvent> {

    private final ObjectMapper objectMapper;

    public TaskEventDeserializer() {
        // Регистрация модуля для поддержки Java 8 времени (например, LocalDateTime)
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Дополнительные настройки, если нужны
    }

    @Override
    public TaskEvent deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            // Десериализуем данные в объект TaskEvent
            return objectMapper.readValue(data, TaskEvent.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка десериализации TaskEvent: " + e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        // Закрытие ресурсов, если нужно
    }
}
