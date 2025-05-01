package com.kodilla.sudoku;

import java.util.HashMap;
import java.util.Map;

public class MenuEnum {

    public enum MainMenuOption {
        NEW_GAME(1),
        EXIT(2);

        private static final Map<Integer, MainMenuOption> BY_VALUE = new HashMap<>();

        static {
            for (MainMenuOption option : values()) {
                BY_VALUE.put(option.value, option);
            }
        }

        private final int value;

        MainMenuOption(int value) {
            this.value = value;
        }

        public static MainMenuOption fromValue(int value) {
            return BY_VALUE.get(value);
        }
    }
}
