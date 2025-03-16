package com.kodilla.good.patterns.challenges;

import java.util.List;
import java.util.Objects;

public class ProductsRequest {

    private final List<Item> productList;

    public ProductsRequest(List<Item> productList) {
        this.productList = productList;
    }

    public List<Item> getProductList() {
        return productList;
    }

    public int getProductQuantityByName(String itemName) {
        for (Item item : productList)
            if (item.getName().equals(itemName)) return item.getQuantity();
        return 0;
    }

    public double getProductPriceByName(String itemName) {
        for (Item item : productList)
            if (item.getName().equals(itemName)) return item.getPrice();
        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductsRequest that = (ProductsRequest) o;
        return Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productList);
    }
}
