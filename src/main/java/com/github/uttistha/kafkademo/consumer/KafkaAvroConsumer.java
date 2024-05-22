package com.github.uttistha.kafkademo.consumer;

import com.github.uttistha.kafkademo.dto.Employee;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaAvroConsumer {

    Logger logger = org.slf4j.LoggerFactory.getLogger(KafkaAvroConsumer.class);

    @KafkaListener(topics = "${topic.name}" , groupId = "${spring.kafka.consumer.group-id}")
    public void readEmployee(ConsumerRecord<String, Employee> employeeRecord) {
        String key = employeeRecord.key();
        Employee employee = employeeRecord.value();
        logger.info("Key: {} Employee: {}" , key, employee);
    }
}
