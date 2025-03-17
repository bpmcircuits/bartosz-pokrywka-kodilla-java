package com.kodilla.food2door;

import java.util.Objects;

public class Product {

    private final String productType;
    private final int amount;

    public Product(String productType, int amount) {
        this.productType = productType;
        this.amount = amount;
    }

    public String getProductType() {
        return productType;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return amount == product.amount && Objects.equals(productType, product.productType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, amount);
    }
}
