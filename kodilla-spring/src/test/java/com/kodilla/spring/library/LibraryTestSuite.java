
package com.kodilla.spring.library;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryTestSuite {

    @Autowired
    private Library library;

    @Test
    void testLoadFromDb() {
        //given
        //when
        library.loadFromDb();
        //then
        //do nothing
    }

    @Test
    void saveToDb() {
        //given
        //when
        library.saveToDb();
        //then
        //do nothing
    }

    @Test
    void testContext() {
        //given
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.kodilla.spring");
        //when then
        System.out.println("=======Beans List=========");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println("=======Beans List=========");
    }

}