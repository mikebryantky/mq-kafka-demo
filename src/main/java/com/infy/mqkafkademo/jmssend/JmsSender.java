package com.infy.mqkafkademo.jmssend;

import com.infy.mqkafkademo.Message;
import com.infy.mqkafkademo.config.JsonMarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * STEP ONE: Generate a message and send to ActiveMQ Queue.
 */
@Service
@Slf4j
public class JmsSender {
    private final JmsTemplate jmsTemplate;
    private final JsonMarshaller jsonMarshaller;
    @Value("${jms.queue.name}")
    private String queueName;

    public JmsSender(JmsTemplate jmsTemplate, JsonMarshaller jsonMarshaller) {
        this.jmsTemplate = jmsTemplate;
        this.jsonMarshaller = jsonMarshaller;
    }

    public void sendMessage(Message message) {
        String json = jsonMarshaller.marshal(message);
        log.debug("Sending to queue {}: {}", queueName, json);
        jmsTemplate.send(queueName, session -> {
            javax.jms.Message sendMessage = session.createTextMessage(json);
            sendMessage.setJMSCorrelationID(UUID.randomUUID().toString());
            return sendMessage;
        });
    }

    /**
      After initially waiting five seconds, send a message
      every five seconds to the JMS Queue.
     */
    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void scheduler() {
        sendMessage(new Message());
    }
}
