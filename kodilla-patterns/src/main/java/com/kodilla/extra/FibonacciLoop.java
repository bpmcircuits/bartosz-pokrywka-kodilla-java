package com.kodilla.extra;

public class FibonacciLoop {

    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        long a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("n-ty element ciągu Fibonacciego to: " + fibonacci(n));
    }
}
