package com.kodilla.good.patterns.challenges;

import java.util.Objects;

public class Item {

    private final String name;
    private final int quantity;
    private final double price;

    public Item(String item, int quantity, double price) {
        this.name = item;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item1 = (Item) o;
        return Objects.equals(name, item1.name) && Objects.equals(quantity, item1.quantity) && Objects.equals(price, item1.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price);
    }
}
