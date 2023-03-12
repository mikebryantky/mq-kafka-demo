package com.infy.mqkafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MqKafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqKafkaDemoApplication.class, args);
    }

}
