package com.kodilla.food2door;

public class DiscordService implements InformationService {
    public void displayOrderSuccess(String courierName) {
        System.out.printf("Order from %s was successful", courierName);
    }

}
