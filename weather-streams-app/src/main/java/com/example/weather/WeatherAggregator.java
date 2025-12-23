package com.example.weather;

public class WeatherAggregator {
    private double sumTemp = 0.0;
    private double sumHumidity = 0.0;
    private int count = 0;

    public WeatherAggregator() {}

    public WeatherAggregator add(WeatherRecord record) {
        sumTemp += record.getTemperature();
        sumHumidity += record.getHumidity();
        count++;
        return this;
    }

    public WeatherRecord toWeatherRecord(String station) {
        double avgTemp = count == 0 ? 0 : sumTemp / count;
        double avgHumidity = count == 0 ? 0 : sumHumidity / count;
        return new WeatherRecord(station, avgTemp, avgHumidity);
    }
}
