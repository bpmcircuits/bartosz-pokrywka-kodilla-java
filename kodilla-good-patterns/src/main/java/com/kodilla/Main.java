package com.kodilla;

import com.kodilla.good.patterns.challenges.Factorial;
import com.kodilla.good.patterns.challenges.MovieStore;

import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MovieStore movieStore = new MovieStore();

        List<String> movies = movieStore.getMovies()
                .values()
                .stream()
                .flatMap(Collection::stream)
                .toList();

        System.out.println(String.join(" ! ", movies));

        System.out.println(Factorial.factorial(movies.size()));
    }

}
