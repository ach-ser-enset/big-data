package com.example.weather;

import org.apache.kafka.common.serialization.Serializer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class WeatherRecordSerializer implements Serializer<WeatherRecord> {
    @Override
    public byte[] serialize(String topic, WeatherRecord data) {
        if (data == null) return null;
        return data.toString().getBytes(StandardCharsets.UTF_8);
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
    @Override
    public void close() {}
}
