package com.example.weather;

import org.apache.kafka.common.serialization.Deserializer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class WeatherRecordDeserializer implements Deserializer<WeatherRecord> {
    @Override
    public WeatherRecord deserialize(String topic, byte[] data) {
        if (data == null) return null;
        String line = new String(data, StandardCharsets.UTF_8);
        return WeatherRecord.fromString(line);
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
    @Override
    public void close() {}
}
