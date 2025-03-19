package com.kodilla.spring.portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoardConfig {

    @Autowired
    private Board board;

    @Bean
    public Board getBoard() {
        return new Board();
    }

    @Bean
    public TaskList getToDoList() {
        return board.getToDoList();
    }

    @Bean
    public TaskList getInProgressList() {
        return board.getInProgressList();
    }

    @Bean
    public TaskList getDoneList() {
        return board.getDoneList();
    }

}
