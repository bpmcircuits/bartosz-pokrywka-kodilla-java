package com.kodilla.spring.extra;

public class Fibonacci {
    public static long fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(-2));
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(8));

    }
}
