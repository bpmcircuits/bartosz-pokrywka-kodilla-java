package com.kodilla.food2door.shop;

import java.util.Objects;

public class ShopDto {
    private final String courierName;
    private final boolean isTransactionDone;

    public ShopDto(String courierName, boolean isTransactionDone) {
        this.courierName = courierName;
        this.isTransactionDone = isTransactionDone;
    }

    public String getCourierName() {
        return courierName;
    }

    public boolean isTransactionDone() {
        return isTransactionDone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopDto shopDto = (ShopDto) o;
        return isTransactionDone == shopDto.isTransactionDone && Objects.equals(courierName, shopDto.courierName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courierName, isTransactionDone);
    }
}
