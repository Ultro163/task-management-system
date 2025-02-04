package com.example.kafka.service;

import com.example.kafka.model.TaskEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaTemplate<String, Object> taskKafkaTemplate;

    public void sendTaskEventToPartition0(String topicName, TaskEvent taskEvent) {
        log.info("Sending to partition 0: {}", taskEvent);

        ProducerRecord<String, Object> record = new ProducerRecord<>(topicName, 0, null, taskEvent);
        taskKafkaTemplate.send(record);
    }

    public void sendTaskEventToPartition1(String topicName, TaskEvent taskEvent) {
        log.info("Sending to partition 1: {}", taskEvent);

        ProducerRecord<String, Object> record = new ProducerRecord<>(topicName, 1, null, taskEvent);
        taskKafkaTemplate.send(record);
    }
}