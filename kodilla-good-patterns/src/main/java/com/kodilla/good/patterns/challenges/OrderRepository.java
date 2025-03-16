package com.kodilla.good.patterns.challenges;

public interface OrderRepository {
    boolean createOrder(String orderItemName, int amount);
}
