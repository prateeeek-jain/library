package com.lld.library;

import java.time.LocalDateTime;
import java.util.*;

import com.lld.library.records.Book;

public interface Library {
    Book addNewBook(Book book);

    BookCopy addNewCopy(BookCopy bookCopy);

    List<Book> getAllBooks();

    List<Book> searchBookByName(String str);

    List<Book> searchBookByAuthor(String str);

    Member addNewMember(Member member);

    BookCopy borrowBookCopy(Book book, Member member);

    int returnBookCopy(BookCopy bookCopy, Member member, LocalDateTime returnDate);

}
