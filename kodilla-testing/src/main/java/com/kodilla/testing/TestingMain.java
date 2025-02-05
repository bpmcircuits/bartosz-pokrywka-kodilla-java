package com.kodilla.testing;

import com.kodilla.testing.calculator.Calculator;
import com.kodilla.testing.user.SimpleUser;

public class TestingMain {

    public static void main(String[] args) {
        SimpleUser simpleUser = new SimpleUser("theForumUser");

        String result = simpleUser.getUsername();

        if (result.equals("theForumUser")) {
            System.out.println("test OK");
        } else {
            System.out.println("Error!");
        }

        int addResult = Calculator.add(2, 3);

        int subtractResult = Calculator.subtract(3, 2);

        System.out.println("Testing addition: ");
        if (addResult == 5) {
            System.out.println("test OK");
        } else {
            System.out.println("Error!");
        }

        System.out.println("Testing subtraction: ");
        if (subtractResult == 1) {
            System.out.println("test OK");
        } else {
            System.out.println("Error!");
        }
    }
}