package com.kodilla.checkers.ui;

import com.kodilla.checkers.logic.Move;

import java.awt.*;
import java.util.Scanner;

public class UserInterface {

    private static Scanner scanner = new Scanner(System.in);

    public static int mainMenuChoice() {
        int menuChoice = -1;
        System.out.println(UIStrings.MAIN_MENU);
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(UIStrings.OPTION);
                String input = scanner.nextLine().trim();
                menuChoice = Integer.parseInt(input);
                if (menuChoice < 1 || menuChoice > 2) {
                    System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_TWO);
                    continue;
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_TWO);
            }
        }
        return menuChoice;
    }

    public static boolean onExit() {
        boolean validOption = false;
        while (!validOption) {
            System.out.print(UIStrings.ON_QUIT);
            String userExitQ = scanner.nextLine().trim();
            if (!userExitQ.equals("yes") && !userExitQ.equals("no")) {
                System.out.println(UIStrings.WRONG_OPTION);
                continue;
            }
            if (userExitQ.equals("no")) return false;
            validOption = true;
        }
        return true;
    }

    public static int newGameMenu() {

        int menuChoice = -1;
        System.out.println(UIStrings.NEW_GAME_MENU);
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(UIStrings.OPTION);
                String input = scanner.nextLine().trim();
                menuChoice = Integer.parseInt(input);
                if (menuChoice < 1 || menuChoice > 3) {
                    System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_THREE);
                    continue;
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_THREE);
            }
        }
        return menuChoice;
    }

    public static String choosePlayerName(Settings.PLAYER player) {
        System.out.println(player == Settings.PLAYER.FIRST ? UIStrings.PLAYER_ONE_NAME : UIStrings.PLAYER_TWO_NAME);
        return scanner.nextLine().trim();
    }

    public static String chooseFigure() {
        String figure = "";

        boolean validFigure = false;
        while (!validFigure) {
            System.out.println(UIStrings.CHOOSE_FIGURE);
            figure = scanner.nextLine().trim().toUpperCase();

            if (figure.equals("B") || figure.equals("W")) {
                validFigure = true;
            } else {
                System.out.println(UIStrings.WRONG_OPTION);
            }
        }
        return figure;
    }

    public static void displayPlayers(String playerOne, String playerTwo) {
        System.out.printf(UIStrings.PLAYER_VS_PLAYER, playerOne, playerTwo);
    }


    public static MenuEnum.ComputerLevelEnum chooseComputerLevel() {
        int levelChoice = -1;
        boolean validComputerLevel = false;
        while (!validComputerLevel) {
            System.out.println(UIStrings.COMPUTER_LEVEL);
            System.out.print(UIStrings.OPTION);
            String input = scanner.nextLine().trim();
            try {
                levelChoice = Integer.parseInt(input);
                if (levelChoice < 1 || levelChoice > 2) {
                    System.out.println(UIStrings.CHOOSE_RIGHT_OPTION_ONE_TWO);
                    continue;
                }
                validComputerLevel = true;
            } catch (NumberFormatException e) {
                System.out.println(UIStrings.WRONG_OPTION);
            }
        }
        return levelChoice == 1 ? MenuEnum.ComputerLevelEnum.EASY : MenuEnum.ComputerLevelEnum.HARD;
    }

    public static void showWhichPlayerTurn(String playerName) {
        System.out.printf(UIStrings.PLAYER_TURN, playerName);
    }

    public static Move getPlayerMove() {
        String move = "";
        boolean validPlace = false;
        while (!validPlace) {
            System.out.print(UIStrings.PLACE_YOUR_MOVE);
            move = scanner.nextLine().trim();
            if (isCorrectMoveName(move)) {
                validPlace = true;
            }
            else System.out.println(UIStrings.WRONG_MOVE_NAME);
        }
        return takeMove(move);
    }

    public static void showWinner(String playerName) {
        System.out.printf(UIStrings.WINNER, playerName);
    }

    public static void showDraw() {
        System.out.println(UIStrings.DRAW);
    }

    public static void illegalMove() {
        System.out.println(UIStrings.ILLEGAL_MOVE);
    }

    private static boolean isCorrectPositionName(String position) {
        if (position == null || position.length() < 2) {
            return false;
        }
        char colChar = position.charAt(0);
        if (colChar < 'A' || colChar > ('A' + Settings.BOARD_SIZE - 1)) {
            return false;
        }
        String rowPart = position.substring(1);
        try {
            int row = Integer.parseInt(rowPart);
            return row >= 1 && row <= Settings.BOARD_SIZE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isCorrectMoveName(String move) {
        if (move == null || move.length() < 4) {
            return false;
        }

        return move.matches("^[A-H][1-8][A-H][1-8]$");
    }

    public static Point findPoint(String position) {
        char colChar = position.charAt(0);
        int col = colChar - 'A';
        int row = Character.getNumericValue(position.charAt(1)) - 1;
        return new Point(col, row);
    }

    public static Move takeMove(String move) {
        char fromColChar = move.charAt(0);
        int fromCol = fromColChar - 'A';
        int fromRow = Character.getNumericValue(move.charAt(1)) - 1;

        char toColChar = move.charAt(2);
        int toCol = toColChar - 'A';
        int toRow = Character.getNumericValue(move.charAt(3)) - 1;
        return new Move(new Point(fromCol, fromRow), new Point(toCol, toRow));
    }

}
