package com.kodilla.exception.test;

import java.util.HashMap;
import java.util.Map;

public class Flyinder {

    private static final Map<String, Boolean> airports = new HashMap<>();
    static {
        airports.put("Pyrzowice", true);
        airports.put("Los Angeles International Airport", true);
        airports.put("Heathrow Airport", true);
        airports.put("Charles de Gaulle Airport", true);
        airports.put("Frankfurt Airport", true);
        airports.put("Dubai International Airport", true);

        airports.put("Muchowiec", false);
        airports.put("Groom Lake Airfield (Area 51)", false);
        airports.put("Chernobyl Exclusion Zone Airstrip", false);
        airports.put("NASA Shuttle Landing Facility", false);
        airports.put("Nellis Air Force Base Airfield", false);
        airports.put("Edwards Air Force Base", false);
    }

    public String findFlight(Flight flight) throws RouteNotFoundException {

        if (airports.containsKey(flight.getDepartureAirport())
                && airports.containsKey(flight.getArrivalAirport())) {
            if (airports.get(flight.getDepartureAirport()).equals(true)
                    && airports.get(flight.getArrivalAirport()).equals(true)) {
                return "Route found!";
            } else {
                throw new RouteNotFoundException("Airports may not be available!");
            }

        } else {
            throw new RouteNotFoundException("Route not found! - "
                    + flight.getDepartureAirport() + " - " + flight.getArrivalAirport());
        }
    }

}
