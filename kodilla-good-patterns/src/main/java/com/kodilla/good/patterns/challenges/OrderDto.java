package com.kodilla.good.patterns.challenges;

import java.util.Objects;

public class OrderDto {
    private final ProductOrder order;
    private final boolean isOrdered;

    public OrderDto(ProductOrder order, boolean isOrdered) {
        this.order = order;
        this.isOrdered = isOrdered;
    }

    public ProductOrder getOrder() {
        return order;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return isOrdered == orderDto.isOrdered && Objects.equals(order, orderDto.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, isOrdered);
    }
}
