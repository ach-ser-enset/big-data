package com.example.clickproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClickKafkaProducer {
    private static final String TOPIC = "clicks";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendClick(String userId) {
        kafkaTemplate.send(TOPIC, userId, "click");
    }
}
