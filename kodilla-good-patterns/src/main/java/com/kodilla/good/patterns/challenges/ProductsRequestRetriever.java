package com.kodilla.good.patterns.challenges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsRequestRetriever {

    public ProductsRequest retrieve() {
        List<Item> productList = new ArrayList<>();
        productList.add(new Item("Notebook", 20, 10.99));
        productList.add(new Item("Lamp", 5, 54.32));
        productList.add(new Item("Computer", 1, 599.99));

        return new ProductsRequest(productList);
    }
}
