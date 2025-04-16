package com.kodilla.extra.weightedaverage;

import java.util.List;
import java.util.stream.IntStream;

public class WeightedAvg {

    private final List<Integer> grades;
    private final List<Integer> weights;
    private final static int MIN_GRADE = 1;
    private final static int MAX_GRADE = 6;
    private final static int MIN_WEIGHT = 1;
    private final static int MAX_WEIGHT = 10;

    public WeightedAvg(List<Integer> grades, List<Integer> weights) {
        if (grades.size() != weights.size()) throw new IllegalArgumentException("Lists must have the same size.");
        if (grades.stream().anyMatch(grade -> grade < MIN_GRADE || grade > MAX_GRADE))
            throw new IllegalArgumentException("Grade must be between 1 and 6.");
        if (weights.stream().anyMatch(weight -> weight < MIN_WEIGHT || weight > MAX_WEIGHT))
            throw new IllegalArgumentException("Weight must be between 1 and 10.");
        if (grades.isEmpty()) throw new IllegalArgumentException("Lists must have at least one element.");

        this.grades = grades;
        this.weights = weights;
    }

    public double getAverage() {

        double sumA = IntStream.range(0, grades.size())
                .mapToDouble(i -> grades.get(i) * weights.get(i))
                .sum();
        double sumB = weights.stream().mapToInt(Integer::intValue).sum();

        return sumA / sumB;


    }
}
