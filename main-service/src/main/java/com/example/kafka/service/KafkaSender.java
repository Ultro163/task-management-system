package com.example.kafka.service;

import com.example.kafka.model.TaskEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, Object> taskKafkaTemplate;

    public void sendNewTaskEvent(String topicName, TaskEvent taskEvent) {
        log.info("Sending to partition 0: {}", taskEvent);

        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topicName, 0, null, taskEvent);
        taskKafkaTemplate.send(producerRecord);
    }

    public void sendUpdatedTaskEvent(String topicName, TaskEvent taskEvent) {
        log.info("Sending to partition 1: {}", taskEvent);

        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topicName, 1, null, taskEvent);
        taskKafkaTemplate.send(producerRecord);
    }

    public void sendDeletedTaskEvent(String topicName, UUID taskId) {
        log.info("Sending to partition 2: {}", taskId);
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topicName, 2, null, taskId);
        taskKafkaTemplate.send(producerRecord);
    }
}