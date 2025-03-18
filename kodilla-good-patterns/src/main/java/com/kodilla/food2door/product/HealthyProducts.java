package com.kodilla.food2door.product;

public class HealthyProducts implements Product {
    private final int amount;

    public HealthyProducts(int amount) {
        this.amount = amount;
    }

    @Override
    public String getProductType() {
        return "Healthy Products";
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
