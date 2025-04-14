package com.kodilla.patterns.builder.bigmac;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigmacTestSuite {

    @Test
    void testBuildTheBurger() {
        //given
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .bun("sesame")
                .sauce("barbecue")
                .ingredients("pickles")
                .ingredients("onion")
                .burgers(1)
                .bun("plain")
                .sauce("mayo")
                .burgers(1)
                .build();
        System.out.println(bigmac);
        //when
        String actual = bigmac.getSauce();
        //then
        assertEquals("barbecue", actual);
    }
}