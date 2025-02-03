package com.example.service;

import com.example.model.TaskEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, TaskEvent> taskKafkaTemplate;

    public void sendTaskEvent(String topicName, TaskEvent taskEvent) {
        log.info("Sending : {}", taskEvent);
        log.info("--------------------------------");

        taskKafkaTemplate.send(topicName, taskEvent);
    }
}