package com.kodilla.patterns.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTestSuite {

    private Logger logger;

    @Test
    void testLogger() {
        //given
        logger = Logger.LOGGER;
        logger.log("Hello World");
        //when
        String actualLog = logger.getLastLog();
        //then
        assertEquals("Hello World", actualLog);
    }

    @Test
    void testLoggerEqualsAnotherLogger() {
        //given
        logger = Logger.LOGGER;
        //when
        Logger logger2 =  Logger.LOGGER;
        //then
        assertEquals(logger2, logger);
    }


}