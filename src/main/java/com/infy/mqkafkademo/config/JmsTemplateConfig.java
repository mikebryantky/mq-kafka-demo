package com.infy.mqkafkademo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
@Slf4j
public class JmsTemplateConfig {
    @Value("${spring.jms.template.receive-timeout}")
    private Long receiveTimeout;

    @Value("${spring.jms.template.time-to-live}")
    private Long ttl;

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        try {
            jmsTemplate.setReceiveTimeout(receiveTimeout);
            jmsTemplate.setMessageTimestampEnabled(true);
            jmsTemplate.setTimeToLive(ttl);
            connectionFactory.createConnection().createSession();
        } catch (JMSException e) {
            log.error(e.getMessage(), e);
        }
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }

}
