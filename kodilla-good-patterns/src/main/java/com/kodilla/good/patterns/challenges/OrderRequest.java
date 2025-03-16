package com.kodilla.good.patterns.challenges;

import java.util.Objects;

public class OrderRequest {

    private final ProductOrder order;

    public OrderRequest(ProductOrder order) {
        this.order = order;
    }

    public ProductOrder getOrder() {
        return order;
    }

    public int getOrderItemAmount() {
        return order.getAmount();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequest that = (OrderRequest) o;
        return Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(order);
    }
}
