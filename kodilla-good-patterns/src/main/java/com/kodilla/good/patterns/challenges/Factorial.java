package com.kodilla.good.patterns.challenges;

public class Factorial {

    public static long factorial(int number) {
        if (number < 1) throw new IllegalArgumentException();
        if (number == 1) return 1;
        return number * factorial(number - 1);
    }
}
