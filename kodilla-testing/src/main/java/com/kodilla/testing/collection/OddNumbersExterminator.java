package com.kodilla.testing.collection;

import java.util.List;

public class OddNumbersExterminator {

    public List<Integer> exterminate(List<Integer> numbers) {
        return !numbers.isEmpty() ? numbers.stream()
                .filter(number -> number % 2 == 0)
                .toList() : null;
    }
}
