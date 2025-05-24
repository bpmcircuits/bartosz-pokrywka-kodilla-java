package com.kodilla.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class BasicPizzaOrder implements PizzaOrder {
    @Override
    public BigDecimal getCost() {
        return new BigDecimal(10);
    }
    @Override
    public String getIngredients() {
        return "Dough, tomato sauce, cheese";
    }
}
