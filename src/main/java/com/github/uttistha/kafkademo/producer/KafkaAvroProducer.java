package com.github.uttistha.kafkademo.producer;


import com.github.uttistha.kafkademo.dto.Employee;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaAvroProducer {

    Logger logger = org.slf4j.LoggerFactory.getLogger(KafkaAvroProducer.class);
    @Value("${topic.name}")
    private String topicName;
    private final KafkaTemplate<String, Employee> kafkaTemplate;

    public KafkaAvroProducer(KafkaTemplate<String, Employee> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventToTopic(Employee employee) {
        CompletableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(topicName, UUID.randomUUID().toString(),employee);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                logger.error("Unable to send message: {}" , employee);
            } else {
                logger.info("Message sent: {}" , employee);
            }
        });
    }



}
