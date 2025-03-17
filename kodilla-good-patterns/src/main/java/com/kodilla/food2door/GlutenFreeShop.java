package com.kodilla.food2door;

public class GlutenFreeShop implements Courier {

    private final static int AMOUNT = 50;
    private final Product product = new Product("Gluten Free Products", AMOUNT);
    private final int orderedAmount;

    public GlutenFreeShop(int orderedAmount) {
        this.orderedAmount = orderedAmount;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public String getName() {
        return "Gluten Free Shop";
    }

    @Override
    public boolean process() {
        return orderedAmount < AMOUNT;
    }
}
