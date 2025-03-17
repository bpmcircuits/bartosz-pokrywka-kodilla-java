package com.kodilla.food2door.shop;

import com.kodilla.food2door.product.Product;
import com.kodilla.food2door.product.ProductOrder;

public interface Shop {
    Product getProduct();
    String getName();
    boolean process(ProductOrder productOrder);
}
