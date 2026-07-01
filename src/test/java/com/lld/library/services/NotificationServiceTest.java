package com.lld.library.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lld.library.Member;
import com.lld.library.enums.MemberShipType;
import com.lld.library.enums.NotificationChannel;
import com.lld.library.notifier.EmailNotifier;
import com.lld.library.notifier.SmsNotifier;
import com.lld.library.notifier.WhatsappNotifier;

public class NotificationServiceTest {
    private NotificationService notificationService;

    @BeforeEach
    public void registerNotifier() {
        this.notificationService = new NotificationService();
        this.notificationService.registerNotifier(NotificationChannel.EMAIL, new EmailNotifier());
        this.notificationService.registerNotifier(NotificationChannel.SMS, new SmsNotifier());
        this.notificationService.registerNotifier(NotificationChannel.WHATSAPP, new WhatsappNotifier());
    }

    @Test
    public void checkEmailNotification() {
        Member member = new Member("M01", "Alice", "alice@xyz.com", MemberShipType.REGULAR, NotificationChannel.EMAIL);
        this.notificationService.notifyMember(member, "Test Message");
    }

    @Test
    public void checkSmsNotification() {
        Member member = new Member("M01", "Alice", "alice@xyz.com", MemberShipType.REGULAR, NotificationChannel.SMS);
        this.notificationService.notifyMember(member, "Test Message");
    }

    @Test
    public void checkWhatsappNotification() {
        Member member = new Member("M01", "Alice", "alice@xyz.com", MemberShipType.REGULAR,
                NotificationChannel.WHATSAPP);
        this.notificationService.notifyMember(member, "Test Message");
    }
}
