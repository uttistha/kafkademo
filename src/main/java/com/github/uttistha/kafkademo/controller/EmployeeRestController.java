package com.github.uttistha.kafkademo.controller;

import com.github.uttistha.kafkademo.dto.Employee;
import com.github.uttistha.kafkademo.producer.KafkaAvroProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRestController {

    private final KafkaAvroProducer kafkaAvroProducer;

    public EmployeeRestController(KafkaAvroProducer kafkaAvroProducer) {
        this.kafkaAvroProducer = kafkaAvroProducer;
    }

    @PostMapping("/employee")
    public String sendMessage(@RequestBody Employee employee) {
        kafkaAvroProducer.sendEventToTopic(employee);
        return "Message sent: " + employee;
    }

}
