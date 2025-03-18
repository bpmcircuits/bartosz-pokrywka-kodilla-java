package com.kodilla.spring.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
class CalculatorTestSuite {

    @Test
    void testCalculations() {
        //given
        ApplicationContext context = new AnnotationConfigApplicationContext("com.kodilla.spring.calculator");
        Calculator calculator = context.getBean(Calculator.class);
        //when
        double add = calculator.add(3, 2);
        double sub = calculator.sub(3, 2);
        double mul = calculator.mul(3, 2);
        double div = calculator.div(3, 2);
        //then
        assertEquals(5.0, add);
        assertEquals(1.0, sub);
        assertEquals(6.0, mul);
        assertEquals(1.5, div);
    }

}