package com.example.weather;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.util.Properties;

public class WeatherStreamsApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "weather-streams-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> source = builder.stream("weather-data");

        KStream<String, WeatherRecord> parsed = source
            .mapValues(WeatherRecord::fromString)
            .filter((key, value) -> value.getTemperature() > 30);

        KStream<String, WeatherRecord> fahrenheit = parsed
            .mapValues(record -> {
                double tempF = (record.getTemperature() * 9.0 / 5.0) + 32.0;
                return new WeatherRecord(record.getStation(), tempF, record.getHumidity());
            });

        KGroupedStream<String, WeatherRecord> grouped = fahrenheit.groupBy(
            (key, value) -> value.getStation(),
            Grouped.with(Serdes.String(), Serdes.serdeFrom(new WeatherRecordSerializer(), new WeatherRecordDeserializer()))
        );

        KTable<String, WeatherRecord> averages = grouped.aggregate(
            WeatherAggregator::new,
            (station, record, aggregator) -> aggregator.add(record),
            Materialized.with(Serdes.String(), new WeatherAggregatorSerde())
        ).mapValues(WeatherAggregator::toWeatherRecord);

        averages.toStream().mapValues(WeatherRecord::toString).to("station-averages");

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        streams.start();
    }
}
