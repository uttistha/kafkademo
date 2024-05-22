package com.github.uttistha.kafkademo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${topic.name}")
    private String topicName;
    @Bean
    public Map<String, Object> producerConfig() {
        // Producer configuration
        return Map.of(
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true",
                ProducerConfig.ACKS_CONFIG, "all",
                ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 5000,
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 200,
                ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 500
        );
   }

   @Bean
   public NewTopic createCustomerTopic() {
       // Create topics
       return new NewTopic("customer", 3, (short) 1);
   }

   @Bean
   public NewTopic createEmployeeTopic() {
       // Create topics
       return new NewTopic(topicName, 3, (short) 1);
   }
}
