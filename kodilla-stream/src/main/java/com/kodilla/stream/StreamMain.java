package com.kodilla.stream;

import com.kodilla.stream.beautifier.PoemBeautifier;
import com.kodilla.stream.beautifier.PoemDecorator;
import com.kodilla.stream.lambda.ExpressionExecutor;
import com.kodilla.stream.lambda.Processor;
import com.kodilla.stream.reference.FunctionalCalculator;

public class StreamMain {
    public static void main(String[] args) {
//        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
//
//        System.out.println("Calculating expressions with lambdas");
//        expressionExecutor.executeExpression(10, 5, ((a, b) -> a + b));
//        expressionExecutor.executeExpression(10, 5, ((a, b) -> a - b));
//        expressionExecutor.executeExpression(10, 5, ((a, b) -> a * b));
//        expressionExecutor.executeExpression(10, 5, ((a, b) -> a / b));
//
//        System.out.println("Calculating expressions with method reference");
//        expressionExecutor.executeExpression(3, 6, FunctionalCalculator::addAToB);
//        expressionExecutor.executeExpression(3, 6, FunctionalCalculator::subBFromA);
//        expressionExecutor.executeExpression(3, 6, FunctionalCalculator::multiplyAByB);
//        expressionExecutor.executeExpression(3, 6, FunctionalCalculator::divideAByB);

        PoemBeautifier poemBeautifier = new PoemBeautifier();

        String textToBeautify = "This is a text for this experiment.";

        System.out.println(poemBeautifier.beautify(textToBeautify, text -> text.repeat(3)));
        System.out.println(poemBeautifier.beautify(textToBeautify, String::toUpperCase));
        System.out.println(poemBeautifier.beautify(textToBeautify, text -> text.replace("t", "T")));
        System.out.println(poemBeautifier.beautify(textToBeautify, text -> text.substring(8, 18)));
    }
}
