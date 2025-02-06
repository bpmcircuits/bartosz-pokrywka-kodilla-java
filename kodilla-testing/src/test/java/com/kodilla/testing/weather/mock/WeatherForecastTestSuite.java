package com.kodilla.testing.weather.mock;

import com.kodilla.testing.weather.stub.Temperatures;
import com.kodilla.testing.weather.stub.WeatherForecast;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherForecastTestSuite {

    @Mock
    private Temperatures temperaturesMock;

    @BeforeEach
    void setTemperaturesMap() {
        Map<String, Double> temperaturesMap = Map.of(
                "Rzeszow", 25.5,
                "Krakow", 26.2,
                "Wroclaw", 24.8,
                "Warszawa", 25.2,
                "Gdansk", 26.1
        );

        when(temperaturesMock.getTemperatures()).thenReturn(temperaturesMap);
    }

    @Test
    void testCalculateForecastWithMock() {
        //Given
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);

        //When
        int quantityOfSensors = weatherForecast.calculateForecast().size();

        //Then
        Assertions.assertEquals(5, quantityOfSensors);
    }

    @Test
    void testCalculateAverageTemp() {
        //Given
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);

        //When
        double averageTemperature = weatherForecast.calculateAverageTemperature();

        //Then
        Assertions.assertEquals(25.56, averageTemperature);
    }

    @Test
    void testCalculateMedianTemp() {
        //Given
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);

        //When
        double averageTemperature = weatherForecast.calculateMedianTemperature();

        //Then
        Assertions.assertEquals(25.5, averageTemperature);

    }
}