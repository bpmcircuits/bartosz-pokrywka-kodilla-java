package com.kodilla.patterns2.decorator.taxiportal;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TaxiOrderTestSuite {

    @Test
    void testBasicTaxiOrderGetCost() {
        // Given
        TaxiOrder theOrder = new BasicTaxiOrder();
        // When
        BigDecimal cost = theOrder.getCost();
        // Then
        assertEquals(new BigDecimal(5.00), cost);
    }

    @Test
    void testBasicTaxiOrderGetDescription() {
        // Given
        TaxiOrder theOrder = new BasicTaxiOrder();
        // When
        String description = theOrder.getDescription();
        // Then
        assertEquals("Drive a course", description);
    }

    @Test
    void testTaxiNetworkOrderGetCost() {
        // Given
        TaxiOrder theOrder = new BasicTaxiOrder();
        theOrder = new TaxiNetworkOrderDecorator(theOrder);
        // When
        BigDecimal cost = theOrder.getCost();
        // Then
        assertEquals(new BigDecimal(40.00), cost);
    }

    @Test
    void testTaxiNetworkOrderGetDescription() {
        // Given
        TaxiOrder theOrder = new BasicTaxiOrder();
        theOrder = new TaxiNetworkOrderDecorator(theOrder);
        // When
        String description = theOrder.getDescription();
        // Then
        assertEquals("Drive a course by Taxi Network", description);
    }

    @Test
    void testMyTaxiWithChildSeatGetCost() {
        // Given
        TaxiOrder theOrder = new BasicTaxiOrder();
        theOrder = new MyTaxiNetworkOrderDecorator(theOrder);
        theOrder = new ChildSeatDecorator(theOrder);
        // When
        BigDecimal cost = theOrder.getCost();
        // Then
        assertEquals(new BigDecimal(37), cost);
    }

    @Test
    void testMyTaxiWithChildSeatGetDescription() {
        // Given
        TaxiOrder theOrder = new BasicTaxiOrder();
        theOrder = new MyTaxiNetworkOrderDecorator(theOrder);
        theOrder = new ChildSeatDecorator(theOrder);
        // When
        String description = theOrder.getDescription();
        // Then
        assertEquals("Drive a course by My Taxi Network + child seat", description);
    }

    @Test
    public void testVipTaxiWithChildSeatExpressGetCost() {
        //Given
        TaxiOrder theOrder = new BasicTaxiOrder();
        theOrder = new TaxiNetworkOrderDecorator(theOrder);
        theOrder = new ExpressDecorator(theOrder);
        theOrder = new VipCarDecorator(theOrder);
        theOrder = new ChildSeatDecorator(theOrder);
        System.out.println(theOrder.getCost());
        //When
        BigDecimal theCost = theOrder.getCost();
        //Then
        assertEquals(new BigDecimal(57), theCost);
    }

    @Test
    public void testVipTaxiWithChildSeatExpressGetDescription() {
        //Given
        TaxiOrder theOrder = new BasicTaxiOrder();
        theOrder = new TaxiNetworkOrderDecorator(theOrder);
        theOrder = new ExpressDecorator(theOrder);
        theOrder = new VipCarDecorator(theOrder);
        theOrder = new ChildSeatDecorator(theOrder);
        System.out.println(theOrder.getDescription());
        //When
        String description = theOrder.getDescription();
        //Then
        assertEquals("Drive a course by Taxi Network express service variant VIP + child seat", description);
    }
}