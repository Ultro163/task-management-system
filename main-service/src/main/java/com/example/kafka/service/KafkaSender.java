package com.example.kafka.service;

import com.example.kafka.model.TaskEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, Object> taskKafkaTemplate;

    public void sendTaskEvent(String topicName, TaskEvent taskEvent) {
        log.info("Sending : {}", taskEvent);
        log.info("--------------------------------");

        taskKafkaTemplate.send(topicName, taskEvent);
    }
}