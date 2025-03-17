package com.kodilla.food2door.order;

import com.kodilla.food2door.information.InformationService;
import com.kodilla.food2door.product.ProductOrder;
import com.kodilla.food2door.shop.*;
import com.kodilla.food2door.shop.factory.CourierFactory;
import com.kodilla.food2door.shop.factory.ExtraFoodShopFactory;
import com.kodilla.food2door.shop.factory.GlutenFreeShopFactory;
import com.kodilla.food2door.shop.factory.HealthyShopFactory;

public class OrderService {

    private final InformationService informationService;

    public OrderService(InformationService informationService) {
        this.informationService = informationService;
    }

    public ShopDto order(ProductOrder productOrder) {

        CourierFactory courierFactory = getCourier(productOrder.getShopName());
        Shop shop = courierFactory.createCourier();
        boolean isOrdered = shop.process(productOrder);

        if (isOrdered) {
            informationService.displayOrderSuccess(productOrder.getShopName());
            return new ShopDto(productOrder.getShopName(), true);
        } else {
            return new ShopDto(productOrder.getShopName(), false);
        }
    }

    private CourierFactory getCourier(String shopName) {
        return switch (shopName) {
            case "Extra Food Shop" -> new ExtraFoodShopFactory();
            case "Healthy Shop" -> new HealthyShopFactory();
            case "Gluten Free Shop" -> new GlutenFreeShopFactory();
            default -> throw new IllegalArgumentException("Unexpected value: " + shopName);
        };
    }

}
