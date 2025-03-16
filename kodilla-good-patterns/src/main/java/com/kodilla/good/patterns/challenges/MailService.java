package com.kodilla.good.patterns.challenges;

public class MailService implements InformationService {
    public void inform(String orderedItem, int amount) {
        System.out.printf("Successfully ordered %s, amount: %d. %n", orderedItem, amount);
    }
}
