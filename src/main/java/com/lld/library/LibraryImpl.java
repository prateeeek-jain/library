package com.lld.library;

import java.time.LocalDateTime;
import java.util.*;

import com.lld.library.records.Book;
import com.lld.library.services.Catalog;
import com.lld.library.services.LoanService;
import com.lld.library.services.MemberService;
import com.lld.library.services.NotificationService;

public class LibraryImpl implements Library {
    private Catalog catalog;
    private MemberService memberService;
    private LoanService loanService;
    private final NotificationService notificationService;

    LibraryImpl(NotificationService notificationService) {
        this.catalog = new Catalog();
        this.memberService = new MemberService();
        this.loanService = new LoanService(this.catalog, this.memberService);
        this.notificationService = notificationService;
    }

    @Override
    public Book addNewBook(Book book) {
        return this.catalog.addNewBook(book);
    }

    @Override
    public BookCopy addNewCopy(BookCopy bookCopy) {
        return this.catalog.addNewCopy(bookCopy);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.catalog.getAllBooks();
    }

    @Override
    public List<Book> searchBookByName(String str) {
        return this.catalog.searchBookByName(str);
    }

    @Override
    public List<Book> searchBookByAuthor(String str) {
        return this.catalog.searchBookByAuthor(str);
    }

    @Override
    public Member addNewMember(Member member) {
        this.memberService.addNewMember(member);

        this.notificationService.notifyMember(member, member.getType().welcomeMessage(member));

        return member;
    }

    @Override
    public BookCopy borrowBookCopy(Book book, Member member) {
        return this.loanService.borrowBookCopy(book, member);
    }

    @Override
    public int returnBookCopy(BookCopy bookCopy, Member member, LocalDateTime returnDate) {
        int fine = this.loanService.returnBookCopy(bookCopy, member, returnDate);

        this.notificationService.notifyMember(member, "Book has been returned and please pay the fine amt: " + fine);

        return fine;
    }
}
