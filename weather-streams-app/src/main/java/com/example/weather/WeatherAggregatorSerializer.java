package com.example.weather;

import org.apache.kafka.common.serialization.Serializer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class WeatherAggregatorSerializer implements Serializer<WeatherAggregator> {
    @Override
    public byte[] serialize(String topic, WeatherAggregator data) {
        if (data == null) return null;
        // Serialize as sumTemp,sumHumidity,count
        String str = data.toWeatherRecord("").getTemperature() + "," +
                     data.toWeatherRecord("").getHumidity() + "," +
                     data.count;
        return str.getBytes(StandardCharsets.UTF_8);
    }
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
    @Override
    public void close() {}
}
