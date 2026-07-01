package com.lld.library;

import java.util.Objects;

import com.lld.library.enums.MemberShipType;
import com.lld.library.enums.NotificationChannel;

public class Member {
    private String Id;

    private String name;

    private String email;

    private MemberShipType type;

    private NotificationChannel notificationPreference;

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public MemberShipType getType() {
        return this.type;
    }

    public NotificationChannel getNotificationPreference() {
        return this.notificationPreference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        Member member = (Member) o;

        return member.Id == this.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    public Member(String Id, String name, String email, MemberShipType type,
            NotificationChannel notificationPreference) {
        this.Id = Id;

        this.name = name;

        this.email = email;

        this.type = type;

        this.notificationPreference = notificationPreference;
    }

    public Member(String Id, String name, String email, MemberShipType type) {
        this(Id, name, email, type, NotificationChannel.WHATSAPP);
    }
}
