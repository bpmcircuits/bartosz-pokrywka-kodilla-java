package com.kodilla.spring.portfolio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardTestSuite {

    @Test
    void testTaskAdd() {
        //given
        ApplicationContext context = new AnnotationConfigApplicationContext(BoardConfig.class);
        Board board = context.getBean(Board.class);
        //when
        boolean isToDoListTaskAdded = board.getToDoList().addTask("Pierwsze");
        boolean isInProgressListTaskAdded = board.getInProgressList().addTask("Drugie");
        boolean isDoneListTaskAdded = board.getDoneList().addTask("Trzecie");
        //then
        assertTrue(isToDoListTaskAdded);
        assertTrue(isInProgressListTaskAdded);
        assertTrue(isDoneListTaskAdded);
        System.out.println("To Do List: " + board.getToDoList().getTasks());
        System.out.println("In Progress List: " + board.getInProgressList().getTasks());
        System.out.println("Done List: " + board.getDoneList().getTasks());
    }
}