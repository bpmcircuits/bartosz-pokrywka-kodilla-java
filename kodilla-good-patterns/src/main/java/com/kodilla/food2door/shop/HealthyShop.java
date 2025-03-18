package com.kodilla.food2door.shop;

import com.kodilla.food2door.product.HealthyProducts;
import com.kodilla.food2door.product.Product;
import com.kodilla.food2door.product.ProductOrder;

public class HealthyShop implements Shop {

    private final static int AMOUNT = 150;
    private final Product product = new HealthyProducts(AMOUNT);

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public String getName() {
        return "Healthy Shop";
    }

    @Override
    public boolean process(ProductOrder productOrder) {
        return productOrder.getAmount() > AMOUNT;
    }
}
