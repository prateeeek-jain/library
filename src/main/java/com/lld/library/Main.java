package com.lld.library;

import java.time.LocalDateTime;

import com.lld.library.enums.BookStatus;
import com.lld.library.enums.MemberShipType;
import com.lld.library.enums.NotificationChannel;
import com.lld.library.notifier.EmailNotifier;
import com.lld.library.notifier.SmsNotifier;
import com.lld.library.notifier.WhatsappNotifier;
import com.lld.library.records.Book;
import com.lld.library.services.NotificationService;

public class Main {
    public static void main(String[] args) {

        Member member1 = new Member("M101", "Alice", "alice@gmail.com", MemberShipType.REGULAR);
        Member member2 = new Member("M102", "Bob", "bob@gmail.com", MemberShipType.PREMIUM);

        Book book1 = new Book("B101", "Book1", "Author1", "Thriller");
        Book book2 = new Book("B102", "Book2", "Author2", "Comedy");

        NotificationService notificationService = new NotificationService();
        notificationService.registerNotifier(NotificationChannel.EMAIL, new EmailNotifier());
        notificationService.registerNotifier(NotificationChannel.SMS, new SmsNotifier());
        notificationService.registerNotifier(NotificationChannel.WHATSAPP, new WhatsappNotifier());

        LibraryImpl library = new LibraryImpl(notificationService);

        library.addNewMember(member1);
        library.addNewMember(member2);

        library.addNewBook(book1);
        library.addNewBook(book2);

        library.addNewCopy(new BookCopy("1", BookStatus.AVAILABLE, book1));
        library.addNewCopy(new BookCopy("2", BookStatus.AVAILABLE, book1));
        library.addNewCopy(new BookCopy("1", BookStatus.AVAILABLE, book2));

        BookCopy copy1 = library.borrowBookCopy(book1, member1);

        BookCopy copy2 = library.borrowBookCopy(book1, member2);

        System.out.println(copy1);
        System.out.println(copy2);

        int fine = library.returnBookCopy(copy1, member1, LocalDateTime.parse("2026-06-04T15:30:00"));

        System.out.println("Your fine: " + fine);
    }
}
