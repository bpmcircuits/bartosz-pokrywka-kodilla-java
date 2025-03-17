package com.kodilla.food2door.shop;

public class ShopInfo {

    private final Shop shop;

    public ShopInfo(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return String.format("%s # Product: %s # Amount: %d",
                shop.getName(), shop.getProduct().getProductType(), shop.getProduct().getAmount());
    }
}
