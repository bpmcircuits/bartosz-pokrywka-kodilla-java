package com.kodilla.spring.reader;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReaderTestSuite {

    @Test
    void testRead() {
        //given
        ApplicationContext context = new AnnotationConfigApplicationContext(ReaderConfig.class);
        Reader reader = context.getBean(Reader.class);
        //when then
        reader.read();
    }

    @Test
    void testConditional() {
        //given
        ApplicationContext context = new AnnotationConfigApplicationContext(ReaderConfig.class);
        //when
        boolean book2Exists = context.containsBean("book2");
        //then
        System.out.println("Bean book2 was found in the container: " + book2Exists);
    }

}