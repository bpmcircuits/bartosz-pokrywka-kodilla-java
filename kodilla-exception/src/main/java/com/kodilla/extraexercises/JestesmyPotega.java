package com.kodilla.extraexercises;

import java.util.stream.IntStream;

public class JestesmyPotega {

    public static long exponent(int number, int power) {
        if (number < 0 || power < 0) throw new IllegalArgumentException("You can't use negative values!");
        if (power == 0) return 1;
        if (power == 1) return number;
        if (number == 0) return 0;
        if (number == 1) return 1;
        return IntStream.range(0, power)
                .mapToLong(i -> number)
                .reduce(1, (a, b) -> a * b);
    }
}
