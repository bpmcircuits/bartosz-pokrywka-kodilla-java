package com.kodilla.good.patterns.challenges;

public class ShopOrderService implements OrderService {

    public boolean order(ProductsRequest productsRequest, OrderRequest orderRequest) {
        return orderRequest.getOrderItemAmount() <= productsRequest.getProductQuantityByName(orderRequest.getOrder().getName());
    }
}
