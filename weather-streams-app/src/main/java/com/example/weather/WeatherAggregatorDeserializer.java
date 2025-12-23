package com.example.weather;

import org.apache.kafka.common.serialization.Deserializer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class WeatherAggregatorDeserializer implements Deserializer<WeatherAggregator> {
    @Override
    public WeatherAggregator deserialize(String topic, byte[] data) {
        if (data == null) return null;
        String line = new String(data, StandardCharsets.UTF_8);
        String[] parts = line.split(",");
        WeatherAggregator agg = new WeatherAggregator();
        if (parts.length == 3) {
            agg.add(new WeatherRecord("", Double.parseDouble(parts[0]), Double.parseDouble(parts[1])));
            agg.count = Integer.parseInt(parts[2]);
        }
        return agg;
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
    @Override
    public void close() {}
}
