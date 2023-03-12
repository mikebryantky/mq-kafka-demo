package com.infy.mqkafkademo.jsmreceive;

import com.infy.mqkafkademo.kafkasender.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * STEP TWO: Receive ActiveMQ message and pass to Kafka Writer
 */
@Service
@Slf4j
public class JmsReceiver {

    private final KafkaSender kafkaSender;

    public JmsReceiver(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @JmsListener(destination = "${jms.queue.name}")
    public void receive(TextMessage receiveMessage, Session session) {
        try {
            String messageText = receiveMessage.getText();
            log.debug("Received: {}", messageText);
            kafkaSender.sendMessage(messageText);
        } catch (JMSException e) {
            log.error(e.getMessage(), e);
        }
    }
}
