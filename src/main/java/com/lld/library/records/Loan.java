package com.lld.library.records;

import java.time.LocalDateTime;

import com.lld.library.BookCopy;
import com.lld.library.Member;

public record Loan(String id, BookCopy bookCopy, LocalDateTime issueDate, LocalDateTime dueDate,
        LocalDateTime returnDate, Member member) {
    public Loan updateReturnDate(LocalDateTime returnDateTime) {
        return new Loan(this.id, this.bookCopy, this.issueDate, this.dueDate, returnDateTime, this.member);
    }
}
