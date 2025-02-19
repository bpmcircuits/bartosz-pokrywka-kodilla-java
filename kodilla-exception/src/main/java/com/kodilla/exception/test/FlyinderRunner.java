package com.kodilla.exception.test;

import java.util.Arrays;

public class FlyinderRunner {

    public static void main(String[] args) {
        Flight[] flights = {
                new Flight("Pyrzowice", "Heathrow Airport"),
                new Flight("Dubai International Airport", "Charles de Gaulle Airport"),
                new Flight("Los Angeles International Airport", "Groom Lake Airfield (Area 51)"),
                new Flight("Dubai International Airport", "Chernobyl Exclusion Zone Airstrip"),
                new Flight("Mars", "Wenus"),
                new Flight("Muchowiec", "Ocean niespokojny")
        };

        Flyinder flyinder = new Flyinder();

        Arrays.stream(flights)
                .map(flight -> processFlight(flyinder, flight))
                .forEach(System.out::println);
    }

    private static String processFlight(Flyinder flyinder, Flight flight) {
        try {
            if (flyinder.findFlight(flight)) return "Route found!";
            else return "Route not available.";
        } catch (RouteNotFoundException e) {
            return "Error: " + e.getMessage();
        }
    }
}
