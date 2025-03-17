package com.kodilla.food2door.shop.factory;

import com.kodilla.food2door.shop.Shop;
import com.kodilla.food2door.shop.ExtraFoodShop;

public class ExtraFoodShopFactory implements CourierFactory {
    @Override
    public Shop createCourier() {
        return new ExtraFoodShop();
    }
}
