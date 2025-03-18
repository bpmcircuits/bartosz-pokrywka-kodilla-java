package com.kodilla.spring.forum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ForumUserTestSuite {

    @Test
    void testGetUsername() {
        //given
        ApplicationContext context = new AnnotationConfigApplicationContext(ForumUser.class);
        ForumUser user = context.getBean(ForumUser.class);
        //when
        String actual = user.getUsername();
        //then
        assertEquals("John Smith", actual);
    }
}