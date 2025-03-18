package com.kodilla.flightcompany;

import java.util.Objects;

public class Flight {

    private final City departureCity;
    private final City arrivalCity;

    public Flight(City departureCity, City arrivalCity) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    @Override
    public String toString() {
        return departureCity + " -> " + arrivalCity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(departureCity, flight.departureCity) && Objects.equals(arrivalCity, flight.arrivalCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureCity, arrivalCity);
    }
}
