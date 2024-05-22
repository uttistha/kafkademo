package com.github.uttistha.kafkademo.consumer;

import com.github.uttistha.kafkademo.dto.Customer;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class CustomerEventConsumer {
    Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerEventConsumer.class);

    @RetryableTopic(attempts = "3", autoCreateTopics = "true")
    @KafkaListener(topics = "customer", groupId = "uttistha-group")
    public void consumeEvent(Customer customer, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        logger.info("Consumed event: {} on topic {}" , customer, topic);
        //simulate a failure scenario using email address validation
        //If email contains "fail" then throw an exception
        //If the consumer is not able to process the message then the message will be reprocessed until the max.poll.interval.ms and finally moved to DLQ
        if (customer.email().contains("fail")) {
            throw new RuntimeException("Simulated error");
        }

    }

}
