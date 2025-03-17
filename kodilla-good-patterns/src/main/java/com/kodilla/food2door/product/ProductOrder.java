package com.kodilla.food2door.product;

import java.time.LocalTime;

public class ProductOrder {

    private final String shopName;
    private String productType;
    private final int amount;
    private LocalTime timeStamp;

    public ProductOrder(String shopName, int amount) {
        this.shopName = shopName;
        this.amount = amount;
    }

    public ProductOrder(String productType, String shopName, int amount) {
        this.productType = productType;
        this.shopName = shopName;
        this.amount = amount;
    }

    public ProductOrder(String productType, String shopName, int amount, LocalTime timeStamp) {
        this.productType = productType;
        this.shopName = shopName;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public String getProductType() {
        return productType;
    }

    public String getShopName() {
        return shopName;
    }

    public int getAmount() {
        return amount;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }
}
