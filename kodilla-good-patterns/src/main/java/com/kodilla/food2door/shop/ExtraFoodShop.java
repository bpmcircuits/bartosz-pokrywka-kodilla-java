package com.kodilla.food2door.shop;

import com.kodilla.food2door.product.Product;
import com.kodilla.food2door.product.ProductOrder;

import java.time.LocalTime;

public class ExtraFoodShop implements Shop {

    private final Product product = new Product("Vegetable Products", 100);
    private final LocalTime deliveryTime = LocalTime.parse("12:00");

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public String getName() {
        return "Extra Food Shop";
    }

    @Override
    public boolean process(ProductOrder productOrder) {
        return productOrder.getTimeStamp().isBefore(deliveryTime);
    }
}
