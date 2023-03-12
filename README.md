# ActiveMQ -> Kafka Demo

## Prerequisites
1. Docker Desktop
2. Java JDK 11
3. Maven

## Running the Demo
1. From the home directory, run the command: \
   `docker-compose up`
2. Once the Docker containers start, run the command: \
   `mvnw spring-boot:run`
3. Every five seconds a randomly generated message will be \
   sent to ActiveMQ, retrieved from ActiveMQ, send to Kafka, \
   then retrieved from Kafka. Each step is logged to the console \
   and also to the _mq-kafka-demo.log_ file.
