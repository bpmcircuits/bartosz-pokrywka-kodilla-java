package com.kodilla.flightcompany;

import java.util.List;
import java.util.stream.Stream;

public class FlightService {

    public List<String> searchFlightsFrom(String cityName) {
        City city = new City(cityName.toUpperCase());
        return FlightsRetriever.availableFlights.stream()
                .filter(flight -> flight.getDepartureCity().equals(city))
                .map(flight -> flight.getArrivalCity().getCity())
                .toList();
    }

    public List<String> searchFlightsTo(String cityName) {
        City city = new City(cityName.toUpperCase());
        return FlightsRetriever.availableFlights.stream()
                .filter(flight -> flight.getArrivalCity().equals(city))
                .map(flight -> flight.getDepartureCity().getCity())
                .toList();
    }

    public List<String> searchFlightsBetween(String departureCityName, String arrivalCityName) {
        City departureCity = new City(departureCityName.toUpperCase());
        City arrivalCity = new City(arrivalCityName.toUpperCase());

        return FlightsRetriever.availableFlights.stream()
                .filter(flightOne -> flightOne.getDepartureCity().equals(departureCity))
                .flatMap(flightOne ->
                        FlightsRetriever.availableFlights.stream()
                                .filter(flightTwo -> flightOne.getArrivalCity().equals(flightTwo.getDepartureCity())
                                        && flightTwo.getArrivalCity().equals(arrivalCity))
                                .map(flightTwo -> Stream.of(flightOne.toString(), flightTwo.toString()))
                )
                .flatMap(s -> s)
                .toList();
    }

}
