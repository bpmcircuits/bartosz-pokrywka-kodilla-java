package com.kodilla.testing.collection;

import java.util.List;
import java.util.stream.Collectors;

public class DigitsRemover {

    public List<Integer> removeDigits(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return null;
        }

        return numbers.stream()
                .filter(number -> !(number >= 0 && number <= 9))
                .collect(Collectors.toList());
    }
}
