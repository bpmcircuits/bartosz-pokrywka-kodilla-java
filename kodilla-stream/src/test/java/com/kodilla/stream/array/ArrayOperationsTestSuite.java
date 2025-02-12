package com.kodilla.stream.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayOperationsTestSuite {

    @Test
    void testArrayOperationsMethod() {
        //Given
        int[] numbersToCalculate = new int[]{2,3,4,6,2,3,1,7,10};

        //When
        double averageResult = ArrayOperations.getAverage(numbersToCalculate);

        //Then

        assertEquals(4.0, averageResult);
    }
}
