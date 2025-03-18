package com.kodilla.food2door.product;

public class VegetableProducts implements Product {
    private final int amount;

    public VegetableProducts(int amount) {
        this.amount = amount;
    }

    @Override
    public String getProductType() {
        return "Vegetable Products";
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
