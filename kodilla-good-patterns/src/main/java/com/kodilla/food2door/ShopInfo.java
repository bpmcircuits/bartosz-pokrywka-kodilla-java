package com.kodilla.food2door;

public class ShopInfo {

    private final Courier courier;

    public ShopInfo(Courier shop) {
        this.courier = shop;
    }

    @Override
    public String toString() {
        return String.format("%s # Product: %s # Amount: %d",
                courier.getName(), courier.getProduct().getProductType(), courier.getProduct().getAmount());
    }
}
