package com.kodilla.food2door.shop;

import com.kodilla.food2door.product.Product;
import com.kodilla.food2door.product.ProductOrder;

public class GlutenFreeShop implements Shop {

    private final static int AMOUNT = 50;
    private final Product product = new Product("Gluten Free Products", AMOUNT);

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public String getName() {
        return "Gluten Free Shop";
    }

    @Override
    public boolean process(ProductOrder productOrder) {
        return productOrder.getAmount() < AMOUNT;
    }
}
