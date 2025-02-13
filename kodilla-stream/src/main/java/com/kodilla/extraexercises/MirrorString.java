package com.kodilla.extraexercises;

import java.util.stream.IntStream;

public class MirrorString {

    public static String mirrorStringClassic(String string) {
        char[] letters = string.toCharArray();
        String result = "";
        for (int i = letters.length - 1; i >= 0; i--) {
            result += letters[i];
        }
        return result;
    }

    public static String mirrorStringSB(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    public static String mirrorStringStream(String string) {
        char[] stringToCharArray = string.toCharArray();
        return IntStream.range(0, string.length())
                .mapToObj(i -> stringToCharArray[string.length() - i - 1])
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

}
