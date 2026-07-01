package com.lld.library.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import com.lld.library.BookCopy;
import com.lld.library.Member;
import com.lld.library.enums.BookStatus;
import com.lld.library.enums.MemberShipType;
import com.lld.library.records.Book;
import com.lld.library.records.Loan;

public class LoanService {
    private List<Loan> loans;
    private Catalog catalog;
    private MemberService memberService;

    public LoanService(Catalog catalog, MemberService memberService) {
        this.loans = new ArrayList<>();
        this.catalog = catalog;
        this.memberService = memberService;
    }

    public List<Loan> findAllActiveLoansByMember(String memberId) {
        if (!this.memberService.checkMember(memberId))
            throw new IllegalArgumentException("No member found");

        return this.loans.stream().filter(
                loan -> loan.member().getId() == memberId && loan.returnDate() == null).toList();
    }

    public List<Book> getBorrowedBooks(Member member) {
        return this.findAllActiveLoansByMember(member.getId()).stream().map(loan -> loan.bookCopy().getBook()).toList();
    }

    public int calculateFine(Loan loan, LocalDateTime returnDate) {
        int days = (int) (Duration.between(loan.issueDate(), returnDate)).toDays();
        return loan.member().getType().calculateAmount(days);
    }

    public BookCopy borrowBookCopy(Book book, Member member) {
        Optional<BookCopy> availableCopy = this.catalog.getAvailableBookCopy(book);
        if (availableCopy.isEmpty())
            throw new IllegalArgumentException("No Available copies found");

        BookCopy bookCopy = availableCopy.get();
        MemberShipType type = member.getType();

        List<Book> borrowedBooks = this.getBorrowedBooks(member);

        if (borrowedBooks.size() >= type.getBookBorrowCount())
            throw new IllegalArgumentException("Borrow limit exceeded, return some books to borrow more");

        for (Book borrowedBook : borrowedBooks) {
            if (borrowedBook.equals(book))
                throw new IllegalArgumentException("Already Borrowing the book");
        }

        bookCopy.setStatus(BookStatus.BORROWED);
        this.loans.add(
                new Loan("" + (this.loans.size() + 1),
                        bookCopy,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(member.getType().getBorrowDays()),
                        null,
                        member));
        return bookCopy;
    }

    public int returnBookCopy(BookCopy bookCopy, Member member, LocalDateTime returnDate) {
        Book book = bookCopy.getBook();

        if (!this.catalog.isBookAvalable(book))
            throw new IllegalArgumentException("Book not found");

        for (int i = 0; i < this.loans.size(); i++) {
            Loan loan = this.loans.get(i);
            if (loan.bookCopy().equals(bookCopy)) {
                loan.bookCopy().setStatus(BookStatus.AVAILABLE);
                this.loans.set(i, loan.updateReturnDate(returnDate));
                return this.calculateFine(loan, returnDate);
            }
        }

        throw new IllegalArgumentException("Borrowed book not found for member");
    }
}
