package com.kodilla.food2door;

import java.time.LocalTime;

public class ExtraFoodShop implements Courier {

    private final Product product = new Product("Vegetable Products", 100);
    private final LocalTime deliveryTime = LocalTime.parse("12:00");
    private final LocalTime time;

    public ExtraFoodShop(LocalTime time) {
        this.time = time;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public String getName() {
        return "Extra Food Shop";
    }

    @Override
    public boolean process() {
        return time.isBefore(deliveryTime);
    }
}
