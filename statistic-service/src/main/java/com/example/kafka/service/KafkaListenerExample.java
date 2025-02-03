package com.example.kafka.service;

import com.example.kafka.model.TaskEvent;
import com.example.service.TaskReportServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerExample {
    private final TaskReportServiceImp taskReportServiceImp;

    @KafkaListener(topics = "topic-1", groupId = "statistic", containerFactory = "kafkaListenerContainerFactory")
    void listener(TaskEvent taskEvent) {
        log.info("Received message [{}] in statistic", taskEvent);
        taskReportServiceImp.create(taskEvent);
    }
}