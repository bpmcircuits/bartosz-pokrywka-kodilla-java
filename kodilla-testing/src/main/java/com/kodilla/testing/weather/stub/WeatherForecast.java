package com.kodilla.testing.weather.stub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherForecast {
    private Temperatures temperatures;

    public WeatherForecast(Temperatures temperatures) {
        this.temperatures = temperatures;
    }

    public Map<String, Double> calculateForecast() {
        Map<String, Double> resultMap = new HashMap<>();

        for (Map.Entry<String, Double> temperature :
                temperatures.getTemperatures().entrySet()) {

            // adding 1 celsius degree to current value
            // as a temporary weather forecast
            resultMap.put(temperature.getKey(), temperature.getValue() + 1.0);
        }
        return resultMap;
    }

    public double calculateAverageTemperature() {
        return temperatures.getTemperatures().values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public double calculateMedianTemperature() {
        List<Double> sortedTemperatures = temperatures
                .getTemperatures()
                .values()
                .stream()
                .sorted().toList();

        double median;
        int size = sortedTemperatures.size();
        if (size % 2 == 0) {
            median = (sortedTemperatures.get(size / 2 - 1) + sortedTemperatures.get(size / 2)) / 2.0;
        } else {
            median = sortedTemperatures.get(size / 2);
        }

        return median;
    }
}