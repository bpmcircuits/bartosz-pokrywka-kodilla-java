package com.kodilla.food2door;

public class OrderService {

    private final Courier courier;
    private final InformationService informationService;

    public OrderService(Courier courier, InformationService informationService) {
        this.courier = courier;
        this.informationService = informationService;
    }

    public String displayCourierInfo() {
        return new ShopInfo(courier).toString();
    }

    public ShopDto order() {
        boolean isOrdered = courier.process();
        if (isOrdered) {
            informationService.displayOrderSuccess(courier.getName());
            return new ShopDto(courier.getName(), true);
        } else {
            return new ShopDto(courier.getName(), false);
        }
    }
}
