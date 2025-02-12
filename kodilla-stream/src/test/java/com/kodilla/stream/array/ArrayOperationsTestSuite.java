package com.kodilla.stream.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayOperationsTestSuite {

    @Test
    void testArrayOperationsMethod() {
        //Given
        int[] numbersToCalculate = new int[]{2,3,4,6,2,3,1,7,10,4,5,1,3,6,3,8,11,15,16,2};

        //When
        double averageResult = ArrayOperations.getAverage(numbersToCalculate);

        //Then

        assertEquals(5.6, averageResult);
    }
}
