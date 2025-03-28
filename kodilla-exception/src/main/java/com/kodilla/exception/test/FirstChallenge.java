package com.kodilla.exception.test;

import org.w3c.dom.ls.LSOutput;

public class FirstChallenge {

    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException();
        }
        return a / b;
    }

    /**
     * This main can throw an ArithmeticException!!!
     * @param args
     */
    public static void main(String[] args) {
        FirstChallenge firstChallenge = new FirstChallenge();

        try {
            System.out.println(firstChallenge.divide(3, 0));
        } catch (ArithmeticException e) {
            System.out.println("You can't divide by zero!");
        } finally {
            System.out.println(firstChallenge.divide(9, 3));
        }
    }
}