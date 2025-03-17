package com.kodilla.food2door.shop.factory;

import com.kodilla.food2door.shop.Shop;
import com.kodilla.food2door.shop.HealthyShop;

public class HealthyShopFactory implements CourierFactory {
    @Override
    public Shop createCourier() {
        return new HealthyShop();
    }
}
