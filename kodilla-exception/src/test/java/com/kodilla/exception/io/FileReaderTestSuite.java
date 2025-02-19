package com.kodilla.exception.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTestSuite {

    @Test
    void testReadFile() {
        // Given
        FileReader fileReader = new FileReader();
        // When & Then
        assertDoesNotThrow(() -> fileReader.readFile());
    }

    @Test
    void whenFileDoesntExistReadFileShouldThrowException() {
        // Given
        FileReader fileReader = new FileReader();
        String fileName = "this_file_doesnt_exist.txt";
        // When & Then
        assertThrows(FileReaderException.class, () -> fileReader.readFile(fileName));
    }

    @Test
    void testReadFileWithName() {
        // Given
        FileReader fileReader = new FileReader();
        // When & Then
        assertAll(
                () -> assertThrows(FileReaderException.class, () -> fileReader.readFile("nope.txt")),
                () -> assertThrows(FileReaderException.class, () -> fileReader.readFile(null)),
                () -> assertDoesNotThrow(() -> fileReader.readFile("names.txt"))
        );
    }
}