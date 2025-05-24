package com.kodilla.patterns2.decorator.pizza;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PizzaOrderTestSuite {

    @Test
    void testMargheritaPizzaOrder() {
        // Given
        PizzaOrder pizzaOrder = new BasicPizzaOrder();
        pizzaOrder = new MargheritaDecorator(pizzaOrder);

        // When
        BigDecimal cost = pizzaOrder.getCost();
        String ingredients = pizzaOrder.getIngredients();

        // Then
        assertEquals(new BigDecimal(15), cost);
        assertEquals("Dough, tomato sauce, cheese, mozzarella cheese, basil", ingredients);
    }

    @Test
    void testQuattroFormaggiPizzaOrder() {
        // Given
        PizzaOrder pizzaOrder = new BasicPizzaOrder();
        pizzaOrder = new QuattroFormaggiDecorator(pizzaOrder);

        // When
        BigDecimal cost = pizzaOrder.getCost();
        String ingredients = pizzaOrder.getIngredients();

        // Then
        assertEquals(new BigDecimal(20), cost);
        assertEquals("Dough, tomato sauce, cheese, mozzarella, gorgonzola, parmesan, goat cheese", ingredients);
    }

    @Test
    void testMargheritaWithQuattroFormaggiPizzaOrder() {
        // Given
        PizzaOrder pizzaOrder = new BasicPizzaOrder();
        pizzaOrder = new MargheritaDecorator(pizzaOrder);
        pizzaOrder = new QuattroFormaggiDecorator(pizzaOrder);

        // When
        BigDecimal cost = pizzaOrder.getCost();
        String ingredients = pizzaOrder.getIngredients();

        // Then
        assertEquals(new BigDecimal(25), cost);
        assertEquals("Dough, tomato sauce, cheese, mozzarella cheese, basil, mozzarella, gorgonzola, parmesan, goat cheese", ingredients);
    }

    @Test
    void testPeperoniPizzaOrder() {
        // Given
        PizzaOrder pizzaOrder = new BasicPizzaOrder();
        pizzaOrder = new PepperoniDecorator(pizzaOrder);

        // When
        BigDecimal cost = pizzaOrder.getCost();
        String ingredients = pizzaOrder.getIngredients();

        // Then
        assertEquals(new BigDecimal(17), cost);
        assertEquals("Dough, tomato sauce, cheese, pepperoni", ingredients);
    }

}