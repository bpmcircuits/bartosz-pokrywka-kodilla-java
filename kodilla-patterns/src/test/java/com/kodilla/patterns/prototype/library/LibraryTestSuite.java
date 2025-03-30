package com.kodilla.patterns.prototype.library;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTestSuite {

    @Test
    void testGetBooks() {
        //given

        Library library = new Library("Book Library");
        Book bookOne = new Book("BookOne", "AuthorOne", LocalDate.of(1990, 1, 1));
        Book bookTwo = new Book("BookTwo", "AuthorTwo", LocalDate.of(1991, 1, 1));
        Book bookThree = new Book("BookThree", "AuthorThree", LocalDate.of(1991, 1, 1));
        Book bookFour = new Book("BookFour", "AuthorFour", LocalDate.of(1991, 1, 1));
        library.getBooks().add(bookOne);
        library.getBooks().add(bookTwo);
        library.getBooks().add(bookThree);

        //shallow copy
        Library libraryShallowClone = null;
        try {
            libraryShallowClone = library.shallowCopy();
            libraryShallowClone.setName("Shallow Copy Library");
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }

        //deep copy
        Library libraryDeepClone = null;
        try {
            libraryDeepClone = library.deepCopy();
            libraryDeepClone.setName("Deep Copy Library");
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }

        //when
        libraryShallowClone.getBooks().remove(bookOne);
        libraryDeepClone.getBooks().add(bookFour);

        //then
        System.out.println(library.getBooks());
        System.out.println(libraryShallowClone.getBooks());
        System.out.println(libraryDeepClone.getBooks());
        assertNotEquals(3, library.getBooks().size());
        assertEquals(2, libraryShallowClone.getBooks().size());
        assertEquals(4, libraryDeepClone.getBooks().size());

    }

}