package com.kodilla.extra.weightedaverage;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Integer> grades = List.of(3,1,1,5,6,4);
        List<Integer> weights = List.of(4,6,8,4,4,10);
        WeightedAvg weightedAvg = new WeightedAvg(grades, weights);
        System.out.println(weightedAvg.getAverage());
    }
}
