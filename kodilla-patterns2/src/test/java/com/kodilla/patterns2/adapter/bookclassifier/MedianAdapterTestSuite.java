package com.kodilla.patterns2.adapter.bookclassifier;

import com.kodilla.patterns2.adapter.bookclassifier.librarya.Book;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MedianAdapterTestSuite {

    @Test
    void publicationYearMedianTest() {
        // Given
        Book bookOne = new Book("AuthorOne", "Title", 1990, "qwerty");
        Book bookTwo = new Book("AuthorTwo", "Title", 1992, "qwerty");
        Book bookThree = new Book("AuthorThree", "Title", 1994, "qwerty");
        Book bookFour = new Book("AuthorFour", "Title", 1996, "qwerty");
        Book bookFive = new Book("AuthorFive", "Title", 1998, "qwerty");
        Set<Book> bookSet = Set.of(bookOne, bookTwo, bookThree, bookFour, bookFive);
        MedianAdapter medianAdapter = new MedianAdapter();
        // When
        int medianPublicationYear = medianAdapter.publicationYearMedian(bookSet);
        // Then
        assertEquals(1994, medianPublicationYear);
    }
}