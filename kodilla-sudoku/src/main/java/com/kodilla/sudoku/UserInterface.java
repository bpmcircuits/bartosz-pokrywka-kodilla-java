package com.kodilla.sudoku;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private static Scanner userInput = new Scanner(System.in);

    public static void welcomeMessage() {
        System.out.println("Welcome to Sudoku game!");
    }

    public static MenuEnum.MainMenuOption mainMenuChoice() {
        System.out.println("1. Start new game");
        System.out.println("2. Exit");
        System.out.println("Choose option: ");
        while (true) {
            String option = userInput.nextLine();
            switch (option) {
                case "1": return MenuEnum.MainMenuOption.NEW_GAME;
                case "2": return MenuEnum.MainMenuOption.EXIT;
                default: System.out.println("Wrong option. Try again.");
            }
        }
    }

    public static int chooseBoardSize() {
        System.out.println("Choose board size: ");
        int boardSize;
        while (true) {
            boardSize = userInput.nextInt();
            userInput.nextLine();
            if (boardSize < 0) {
                System.out.println("Number must be positive. Try again.");
            } else {
                return boardSize;
            }
        }
    }

    public static List<Integer> chooseNumbersOrSolve() {
        System.out.println("Choose numbers to fill in the board or type 'SUDOKU' to solve sudoku.");
        System.out.println("Numbers (first two cords, third number): ");
        while (true) {
            String input = getInputForSettingNumbers();
            if (input.equals("SUDOKU")) return null;
            else if (!isInputGroupsOfThree(input))
                System.out.println("Numbers must be in groups of three. Try again.");
            else if (!isInputDigits(input))
                System.out.println("Numbers must be digits. Try again.");
            else return getIntegerList(input);
        }
    }

    private static boolean isInputDigits(String input) {
        return input.matches("^\\d+$");
    }

    private static boolean isInputGroupsOfThree(String input) {
        return input.length() % 3 == 0;
    }

    private static List<Integer> getIntegerList(String input) {
        return input.chars().mapToObj(c -> c - '0').toList();
    }

    private static String getInputForSettingNumbers() {
        return userInput.nextLine().trim().replaceAll("[\\s,]+", "");
    }

    public static void resetScanner() {
        userInput = new Scanner(System.in);
    }

    public static void wrongNumber() {
        System.out.println("Wrong number!");
    }

}
