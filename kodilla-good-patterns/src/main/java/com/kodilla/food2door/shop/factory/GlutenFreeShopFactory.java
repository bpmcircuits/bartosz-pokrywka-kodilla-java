package com.kodilla.food2door.shop.factory;

import com.kodilla.food2door.shop.Shop;
import com.kodilla.food2door.shop.GlutenFreeShop;

public class GlutenFreeShopFactory implements CourierFactory {
    @Override
    public Shop createCourier() {
        return new GlutenFreeShop();
    }
}
