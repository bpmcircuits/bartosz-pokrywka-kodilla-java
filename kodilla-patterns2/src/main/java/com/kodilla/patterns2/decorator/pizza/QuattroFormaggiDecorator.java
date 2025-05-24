package com.kodilla.patterns2.decorator.pizza;

import java.math.BigDecimal;

public class QuattroFormaggiDecorator extends AbstractPizzaOrderDecorator {
    public QuattroFormaggiDecorator(PizzaOrder pizzaOrder) {
        super(pizzaOrder);
    }
    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(10));
    }
    @Override
    public String getIngredients() {
        return super.getIngredients() + ", mozzarella, gorgonzola, parmesan, goat cheese";
    }
}
