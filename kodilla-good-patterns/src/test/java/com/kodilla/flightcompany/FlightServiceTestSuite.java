package com.kodilla.flightcompany;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightServiceTestSuite {

    @Test
    void testCities() {
        //given
        City city = new City("KATOWICE");
        //when
        String actual = city.getCity();
        //then
        assertEquals("KATOWICE", actual);
    }

    @Test
    void testCityConnections() {
        //given
        City katowice = new City("KATOWICE");
        City warszawa = new City("WARSZAWA");
        Flight flight = new Flight(katowice, warszawa);
        //when
        String actual = flight.toString();
        //then
        assertEquals("KATOWICE -> WARSZAWA", actual);

    }

    @Test
    void testFlightSearchWithEmptyCity() {
        //given
        FlightService flightService = new FlightService();
        //when
        List<String> actual = flightService.searchFlightsFrom("");
        //then
        List<String> expected = new ArrayList<>();
        assertEquals(expected, actual);

    }


    @Test
    void testFlightSearchFromNoSuchCity() {
        //given
        FlightService flightService = new FlightService();
        //when
        List<String> actual = flightService.searchFlightsFrom("Tarnowskie Góry");
        //then
        List<String> expected = new ArrayList<>();
        assertEquals(expected, actual);

    }

    @Test
    void testFlightSearchFromKatowice() {
        //given
        FlightService flightService = new FlightService();
        //when
        List<String> actual = flightService.searchFlightsFrom("Katowice");
        //then
        List<String> expected = List.of("WARSZAWA", "KRAKÓW");
        assertEquals(expected, actual);
    }

    @Test
    void testFlightSearchToEmptyCity() {
        //given
        FlightService flightService = new FlightService();
        //when
        List<String> actual = flightService.searchFlightsTo("");
        //then
        List<String> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void testFlightSearchToNoSuchCity() {
        //given
        FlightService flightService = new FlightService();
        //when
        List<String> actual = flightService.searchFlightsTo("Tarnowskie Góry");
        //then
        List<String> expected = new ArrayList<>();
        assertEquals(expected, actual);

    }

    @Test
    void testFlightSearchToWroclaw() {
        //given
        FlightService flightService = new FlightService();
        //when
        List<String> actual = flightService.searchFlightsTo("WROCŁAW");
        //then
        List<String> expected = List.of("GDAŃSK", "WARSZAWA");
        assertEquals(expected, actual);
    }

    @Test
    void testFlightSearchFromGdanskToKrakowThroughWroclaw() {
        //given
        FlightService flightService = new FlightService();
        //when
        List<String> actual = flightService.searchFlightsBetween("Gdańsk", "Kraków");
        //then
        List<String> expected = List.of("GDAŃSK -> WROCŁAW", "WROCŁAW -> KRAKÓW");
        assertEquals(expected, actual);
    }

    @Test
    void testFlightSearchFromKatowiceToWroclawThroughWarsaw() {
        //given
        FlightService flightService = new FlightService();
        //when
        List<String> actual = flightService.searchFlightsBetween("Katowice", "Wrocław");
        //then
        List<String> expected = List.of("KATOWICE -> WARSZAWA", "WARSZAWA -> WROCŁAW");
        assertEquals(expected, actual);
    }
}
