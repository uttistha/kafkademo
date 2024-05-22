package com.github.uttistha.kafkademo.producer;

import com.github.uttistha.kafkademo.dto.Customer;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CustomerEventProducer {
    Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerEventProducer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CustomerEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventToTopic(Customer event) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("customer", event);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                logger.error("Unable to send message: {}" , event);
            } else {
                logger.info("Message sent: {}" , event);
            }
        });
    }
}
