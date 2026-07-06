package com.lld.library.records;

import com.lld.library.enums.BookType;

public record Book(String ISBN, String title, String author, String genre, BookType type) {
    public boolean isBorrowable() {
        return this.type == BookType.LENDING;
    }
}
