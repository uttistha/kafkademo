package com.github.uttistha.kafkademo.controller;

import com.github.uttistha.kafkademo.dto.Customer;
import com.github.uttistha.kafkademo.producer.CustomerEventProducer;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WebInterface {
    Logger logger = org.slf4j.LoggerFactory.getLogger(WebInterface.class);

    private final CustomerEventProducer customerEventProducer;

    public WebInterface(CustomerEventProducer customerEventProducer) {
        this.customerEventProducer = customerEventProducer;
    }

    @PostMapping ("/customerEvent")
    public void sendCustomerEvent(@RequestBody Customer customer) {
        logger.info("Customer event received from web: {}" , customer);
        customerEventProducer.sendEventToTopic(customer);
    }
}
