package com.kodilla.food2door.information;

public class DiscordService implements InformationService {
    public void displayOrderSuccess(String courierName) {
        System.out.printf("Order from %s was successful! %n", courierName);
    }

}
