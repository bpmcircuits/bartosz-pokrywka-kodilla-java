package com.kodilla.stream.world;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldTestSuite {

    @Test
    void testGetPeopleQuantity() {
        //Given
        World world = new World();

        Continent northAmerica = new Continent();
        northAmerica.addCountry(new Country("USA", new BigDecimal("334900000")));
        northAmerica.addCountry(new Country("Canada", new BigDecimal("40100000")));

        Continent europe = new Continent();
        europe.addCountry(new Country("Poland", new BigDecimal("36690000")));
        europe.addCountry(new Country("Germany", new BigDecimal("84480000")));
        europe.addCountry(new Country("France", new BigDecimal("68170000")));
        europe.addCountry(new Country("Spain", new BigDecimal("48370000")));

        Continent asia = new Continent();
        asia.addCountry(new Country("China", new BigDecimal("1408000000")));
        asia.addCountry(new Country("India", new BigDecimal("1386000000")));
        asia.addCountry(new Country("Filipinos", new BigDecimal("112000000")));
        asia.addCountry(new Country("Vietnam", new BigDecimal("100000000")));

        world.addContinent(northAmerica);
        world.addContinent(europe);
        world.addContinent(asia);

        //When
        BigDecimal totalSumOfPeople = world.getPeopleQuantity();

        //Then
        assertEquals(new BigDecimal("3618710000"), totalSumOfPeople);
    }
}
