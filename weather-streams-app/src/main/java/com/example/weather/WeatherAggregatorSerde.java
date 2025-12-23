package com.example.weather;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class WeatherAggregatorSerde extends Serdes.WrapperSerde<WeatherAggregator> {
    public WeatherAggregatorSerde() {
        super(new WeatherAggregatorSerializer(), new WeatherAggregatorDeserializer());
    }
}
