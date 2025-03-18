package com.kodilla.flightcompany;

import java.util.ArrayList;
import java.util.List;

public class FlightsRetriever {

    public static List<Flight> availableFlights = new ArrayList<>();
    private static final City katowice = new City("KATOWICE");
    private static final City warszawa = new City("WARSZAWA");
    private static final City gdansk = new City("GDAŃSK");
    private static final City wroclaw = new City("WROCŁAW");
    private static final City krakow = new City("KRAKÓW");
    private static final City radom = new City("RADOM");

    static {
        availableFlights.add(new Flight(katowice, warszawa));
        availableFlights.add(new Flight(gdansk, wroclaw));
        availableFlights.add(new Flight(katowice, krakow));
        availableFlights.add(new Flight(radom, gdansk));
        availableFlights.add(new Flight(warszawa, wroclaw));
        availableFlights.add(new Flight(wroclaw, krakow));
        availableFlights.add(new Flight(gdansk, krakow));
    }
}
