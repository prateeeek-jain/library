package com.lld.library.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.lld.library.BookCopy;
import com.lld.library.Member;
import com.lld.library.enums.BookStatus;
import com.lld.library.records.Book;

public class Catalog {
    private Map<Book, List<BookCopy>> books;

    public boolean isBookAvalable(Book book) {
        return this.books.containsKey(book);
    }

    public Book addNewBook(Book book) {
        if (this.isBookAvalable(book))
            throw new IllegalArgumentException("Book is already there");

        this.books.put(book, new ArrayList<>());

        return book;
    }

    public BookCopy addNewCopy(BookCopy bookCopy) {
        Book book = bookCopy.getBook();
        if (this.books.get(book) == null)
            this.addNewBook(book);

        this.books.get(book).add(bookCopy);

        return bookCopy;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(this.books.keySet());
    }

    public List<Book> getAllAvailableBooks(Member member) {
        List<Book> availableBooks = new ArrayList<>();

        for (Map.Entry<Book, List<BookCopy>> entry : this.books.entrySet()) {
            for (BookCopy copy : entry.getValue()) {
                if (copy.getStatus() == BookStatus.AVAILABLE) {
                    availableBooks.add(entry.getKey());
                }
            }
        }

        return availableBooks;
    }

    public Optional<BookCopy> getAvailableBookCopy(Book book) {
        if (!this.isBookAvalable(book))
            throw new IllegalArgumentException("Book Not found");

        for (BookCopy copy : this.books.get(book)) {
            if (copy.getStatus() == BookStatus.AVAILABLE)
                return Optional.ofNullable(copy);
        }

        return null;
    }

    public List<Book> searchBookByName(String str) {
        List<Book> results = new ArrayList<>();
        if (str.equals(""))
            return results;

        for (Book book : this.getAllBooks()) {
            if (book.author().contains(str) || book.title().contains(str)) {
                results.add(book);
            }
        }

        return results;
    }

    public List<Book> searchBookByAuthor(String str) {
        List<Book> results = new ArrayList<>();
        if (str.equals(""))
            return results;

        for (Book book : this.getAllBooks()) {
            if (book.author().contains(str) || book.title().contains(str)) {
                results.add(book);
            }
        }

        return results;
    }

    public Catalog() {
        this.books = new HashMap<>();
    }
}
