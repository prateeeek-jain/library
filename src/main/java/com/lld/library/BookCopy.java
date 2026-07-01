package com.lld.library;

import com.lld.library.enums.BookStatus;
import com.lld.library.records.Book;

public class BookCopy {
    private String Id;

    private BookStatus status;

    private Book book;

    public BookCopy(String Id, BookStatus status, Book book) {
        this.Id = Id;
        this.status = status;
        this.book = book;
    }

    public String getId() {
        return this.Id;
    }

    public BookStatus getStatus() {
        return this.status;
    }

    public Book getBook() {
        return this.book;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}