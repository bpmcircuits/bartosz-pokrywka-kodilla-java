package com.kodilla.food2door.product;

public class GlutenFreeProducts implements Product {
    private final int amount;

    public GlutenFreeProducts(int amount) {
        this.amount = amount;
    }

    @Override
    public String getProductType() {
        return "Gluten Free Products";
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
