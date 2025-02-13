package com.kodilla.rps;

import java.util.Objects;
import java.util.Scanner;

public class RpsRunner {
    public static void main(String[] args) {
        boolean end = false;
        Scanner scanner = new Scanner(System.in);

        while (!end) {

            boolean finishedGame = false;

            System.out.println(UIStrings.INTRO);

            System.out.print(UIStrings.WRITE_NAME);
            String username = scanner.nextLine();

            int amountOfRounds = 1;
            boolean validAmount = false;
            while (!validAmount) {
                System.out.print(UIStrings.WRITE_AMOUNT_OF_ROUNDS);
                String amountInput = scanner.nextLine().trim();
                try {
                    amountOfRounds = Integer.parseInt(amountInput);
                    if (amountOfRounds < 1) {
                        System.out.println(UIStrings.AMOUNT_CANT_BE_NEGATIVE);
                    } else {
                        validAmount = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(UIStrings.PROVIDE_NUMBER);
                }
            }

            int difficultyLevel = 1;
            validAmount = false;
            while (!validAmount) {
                System.out.print(UIStrings.CHOOSE_DIFFICULTY);
                String amountInput = scanner.nextLine().trim();
                try {
                    difficultyLevel = Integer.parseInt(amountInput);
                    if (difficultyLevel < 1 || difficultyLevel > 3) {
                        System.out.println(UIStrings.CHOOSE_NUMBER_ONE_THREE);
                    } else {
                        validAmount = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(UIStrings.PROVIDE_NUMBER);
                }
            }

            User user = new User(username, amountOfRounds);
            Opponent opponent = new Opponent(difficultyLevel);

            boolean validChoice = false;
            while (!validChoice) {
                if (user.getAmountOfWonRounds() >= user.getAmountOfRoundsToWin()
                        || opponent.getAmountOfWonRounds() >= user.getAmountOfRoundsToWin()) {
                    finishedGame = true;
                    break;
                }
                System.out.println(UIStrings.USERMENU);
                System.out.print(UIStrings.OPTION);
                String menuChoice = scanner.nextLine();

                switch (menuChoice) {
                    case "1" -> {
                        Shape rock = new Rock();
                        Shape opponentsShape = opponent.chooseRandomShape(rock);
                        System.out.println(UIStrings.YOUR_CHOICE + rock.getShapeName());
                        System.out.println(UIStrings.OPPONENT_CHOICE + opponentsShape.getShapeName());
                        checkWhoWonTheRound(rock, opponentsShape, user, opponent);
                    }
                    case "2" -> {
                        Shape paper = new Paper();
                        Shape opponentsShape = opponent.chooseRandomShape(paper);
                        System.out.println(UIStrings.YOUR_CHOICE + paper.getShapeName());
                        System.out.println(UIStrings.OPPONENT_CHOICE + opponentsShape.getShapeName());
                        checkWhoWonTheRound(paper, opponentsShape, user, opponent);
                    }
                    case "3" -> {
                        Shape scissors = new Scissors();
                        Shape opponentsShape = opponent.chooseRandomShape(scissors);
                        System.out.println(UIStrings.YOUR_CHOICE + scissors.getShapeName());
                        System.out.println(UIStrings.OPPONENT_CHOICE + opponentsShape.getShapeName());
                        checkWhoWonTheRound(scissors, opponentsShape, user, opponent);
                    }
                    case "x" -> {
                        System.out.println(UIStrings.SURE_TO_QUIT);
                        String exitDecision = scanner.nextLine();
                        if (Objects.equals(exitDecision, "yes")) {
                            end = true;
                            validChoice = true;
                        }
                    }
                    case "n" -> {
                        System.out.println(UIStrings.WANT_TO_START_NEW_GAME);
                        String exitDecision = scanner.nextLine();
                        if (Objects.equals(exitDecision, "yes")) validChoice = true;
                    }
                    default -> System.out.println(UIStrings.WRONG_MENU_CHOICE);
                }
                System.out.printf(UIStrings.STATS,
                        user.getAmountOfWonRounds(), opponent.getAmountOfWonRounds());
            }

            if (finishedGame) {
                if (user.getAmountOfWonRounds() > opponent.getAmountOfWonRounds())
                    System.out.println(UIStrings.WON);
                if (user.getAmountOfWonRounds() < opponent.getAmountOfWonRounds())
                    System.out.println(UIStrings.LOOSE);
                if (user.getAmountOfWonRounds() == opponent.getAmountOfWonRounds())
                    System.out.println(UIStrings.DRAW);

                while (!validChoice) {

                    System.out.println(UIStrings.FINISHED_GAME);
                    System.out.print(UIStrings.OPTION);
                    String menuChoice = scanner.nextLine();

                    if (Objects.equals(menuChoice, "n")) {
                        validChoice = true;
                    } else if (Objects.equals(menuChoice, "x")) {
                        validChoice = true;
                        end = true;
                    } else {
                        System.out.println(UIStrings.WRONG_MENU_CHOICE);
                    }
                }

            }
        }
    }

    private static void checkWhoWonTheRound(Shape shape, Shape opponentsShape, User user, Opponent opponent) {
        if (shape.compareShape(opponentsShape) > 0) {
            System.out.println(UIStrings.USER_WON);
            System.out.println();
            user.addWonRounds();
        } else if (shape.compareShape(opponentsShape) < 0) {
            System.out.println(UIStrings.OPPONENT_WON);
            System.out.println();
            opponent.addWonRounds();
        } else {
            System.out.println(UIStrings.DRAW);
        }
    }
}
