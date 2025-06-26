package com.kodilla.extra.numberconverter;

import java.util.List;

public class FromDecimalToHex {

    private static final List<String> HEX_NUMBERS =
            List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F");

    public static void main(String[] args) {
        int decimalNumber = 123456;
        String hexNumber = convertDecimalToHex(decimalNumber);
        System.out.println("Decimal: " + decimalNumber + " -> Hexadecimal: " + hexNumber);
    }

    private static String convertDecimalToHex(int decimalNumber) {
        StringBuilder hexNumber = new StringBuilder();

        do {
            hexNumber.append(HEX_NUMBERS.get(decimalNumber % 16));
            decimalNumber /= 16;
        } while (decimalNumber % 16 != 0);

        hexNumber.reverse();
        hexNumber.insert(0, "0x");

        return hexNumber.toString();
    }
}
