package com.kodilla.testing.collection;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@DisplayName("TDD: Collection Test Suite")
public class CollectionTestSuite {

    @BeforeEach
    public void before() {
        System.out.println("Test Case: begin");
    }

    @AfterEach
    public void after() {
        System.out.println("Test Case: end");
    }

    @Test
    @DisplayName("Test odd numbers for an empty list.")
    void testOddNumbersExterminatorEmptyList() {
        List<Integer> emptyList = new ArrayList<>();

        OddNumbersExterminator oddNumbersExterminator = new OddNumbersExterminator();

        assertNull(oddNumbersExterminator.exterminate(emptyList));
    }

    @Test
    @DisplayName("Test odd numbers for a range from 1 to 14.")
    void testOddNumbersExterminatorNormalList() {

        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14);
        OddNumbersExterminator oddNumbersExterminator = new OddNumbersExterminator();

        List<Integer> expectedResult = List.of(2,4,6,8,10,12,14);

        assertEquals(expectedResult, oddNumbersExterminator.exterminate(numbers));
    }

    @Test
    @DisplayName("Test digits remover for an empty list.")
    void testDigitsRemoverEmptyList() {
        List<Integer> emptyList = new ArrayList<>();

        DigitsRemover digitsRemover = new DigitsRemover();

        assertNull(digitsRemover.removeDigits(emptyList));
    }

    @Test
    @DisplayName("Test digits remover for a list with various numbers.")
    void testDigitsRemoverNormalList() {
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 20, 100);
        DigitsRemover digitsRemover = new DigitsRemover();

        List<Integer> expectedResult = List.of(10, 11, 12, 20, 100);

        assertEquals(expectedResult, digitsRemover.removeDigits(numbers));
    }
}
