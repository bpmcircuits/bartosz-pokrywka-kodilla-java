package com.kodilla.food2door;

public class HealthyShop implements Courier {

    private final static int AMOUNT = 150;
    private final Product product = new Product("Healthy products", AMOUNT);
    private final int orderedAmount;

    public HealthyShop(int orderedAmount) {
        this.orderedAmount = orderedAmount;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public String getName() {
        return "Healthy Shop";
    }

    @Override
    public boolean process() {
        return orderedAmount > AMOUNT;
    }
}
