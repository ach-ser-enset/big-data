package com.example.weather;

public class WeatherRecord {
    private String station;
    private double temperature;
    private double humidity;

    public WeatherRecord(String station, double temperature, double humidity) {
        this.station = station;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getStation() {
        return station;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return station + "," + temperature + "," + humidity;
    }

    public static WeatherRecord fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) throw new IllegalArgumentException("Invalid input: " + line);
        return new WeatherRecord(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
    }
}
