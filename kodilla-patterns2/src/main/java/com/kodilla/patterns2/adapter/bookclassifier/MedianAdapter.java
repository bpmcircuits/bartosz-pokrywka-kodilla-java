package com.kodilla.patterns2.adapter.bookclassifier;

import com.kodilla.patterns2.adapter.bookclassifier.librarya.Book;
import com.kodilla.patterns2.adapter.bookclassifier.librarya.Classifier;
import com.kodilla.patterns2.adapter.bookclassifier.libraryb.BookSignature;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MedianAdapter extends MedianAdaptee implements Classifier {

    @Override
    public int publicationYearMedian(Set<Book> bookSet) {
        Map<BookSignature, com.kodilla.patterns2.adapter.bookclassifier.libraryb.Book> booksMap = new HashMap<>();
        for (Book book : bookSet) {
            BookSignature bookSignature = new BookSignature(book.getSignature());
            com.kodilla.patterns2.adapter.bookclassifier.libraryb.Book bookFromMap =
                    new com.kodilla.patterns2.adapter.bookclassifier.libraryb.Book(
                            book.getAuthor(),
                            book.getTitle(),
                            book.getPublicationYear());
            booksMap.put(bookSignature, bookFromMap);
        }
        return medianPublicationYear(booksMap);
    }
}
