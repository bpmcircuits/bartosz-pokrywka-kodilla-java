package com.kodilla.patterns.builder.bigmac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bigmac {

    private final String bun;
    private final int burgers;
    private final String sauce;
    private final List<String> ingredients;

    public static class BigmacBuilder {
        private String bun;
        private int burgers;
        private String sauce;
        private final List<String> ingredients = new ArrayList<>();

        public BigmacBuilder bun(final String bun) {
            this.bun = bun;
            return this;
        }

        public BigmacBuilder burgers(final int burgers) {
            this.burgers = burgers;
            return this;
        }

        public BigmacBuilder sauce(final String sauce) {
            this.sauce = sauce;
            return this;
        }

        public BigmacBuilder ingredients(final String ingredients) {
            this.ingredients.add(ingredients);
            return this;
        }

        public Bigmac build() {
            return new Bigmac(bun, burgers, sauce, ingredients);
        }
    }

    private Bigmac(String bun, int burgers, String sauce, List<String> ingredients) {
        this.bun = bun;
        this.burgers = burgers;
        this.sauce = sauce;
        this.ingredients = ingredients;
    }

    public String getBun() {
        return bun;
    }

    public int getBurgers() {
        return burgers;
    }

    public String getSauce() {
        return sauce;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Bigmac{" +
                "ingredients=" + ingredients +
                ", sauce='" + sauce + '\'' +
                ", burgers='" + burgers + '\'' +
                ", bun='" + bun + '\'' +
                '}';
    }
}
