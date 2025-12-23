package com.example.clickconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class ClickCountService {
    private final AtomicLong totalClicks = new AtomicLong(0);

    @KafkaListener(topics = "click-counts", groupId = "click-consumer-group")
    public void listen(String message) {
        try {
            long count = Long.parseLong(message.split(",")[1]);
            totalClicks.set(count);
        } catch (Exception e) {
            // ignore parse errors
        }
    }

    public long getTotalClicks() {
        return totalClicks.get();
    }
}
