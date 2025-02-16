package com.example.kafka.service;

import com.example.kafka.model.TaskEvent;
import com.example.service.TaskReportServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerExample {
    private final TaskReportServiceImp taskReportServiceImp;

    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "STATISTIC-TOPIC", partitions = {"0"}),
            groupId = "statistic",
            containerFactory = "kafkaListenerContainerFactory"
    )
    void listenerPartition0(@Payload TaskEvent taskEvent) {
        log.info("Received message [{}] in statistic from partition 0", taskEvent);
        taskReportServiceImp.create(taskEvent);
    }

    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "STATISTIC-TOPIC", partitions = {"1"}),
            groupId = "statistic",
            containerFactory = "kafkaListenerContainerFactory"
    )
    void listenerPartition1(@Payload TaskEvent taskEvent) {
        log.info("Received message [{}] in statistic from partition 1", taskEvent);
        taskReportServiceImp.update(taskEvent);
    }

    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "STATISTIC-TOPIC", partitions = {"2"}),
            groupId = "statistic",
            containerFactory = "kafkaListenerContainerFactory"
    )
    void listenerPartition2(@Payload UUID taskId) {
        log.info("Received message [{}] in statistic from partition 2", taskId);
        taskReportServiceImp.delete(taskId);
    }
}