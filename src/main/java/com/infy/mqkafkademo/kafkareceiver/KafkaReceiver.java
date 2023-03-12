package com.infy.mqkafkademo.kafkareceiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * STEP FOUR: Receive message from Kafka Topic
 */
@Service
@Slf4j
public class KafkaReceiver {
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        log.info("Received from Kafka: {}", message);
    }
}
