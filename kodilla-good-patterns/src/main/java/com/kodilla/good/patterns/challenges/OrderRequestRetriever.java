package com.kodilla.good.patterns.challenges;

import java.util.ArrayList;
import java.util.List;

public class OrderRequestRetriever {

    public OrderRequest retrieve() {
        return new OrderRequest(new ProductOrder("Notebook", 2));
    }
}
