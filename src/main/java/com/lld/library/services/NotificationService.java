package com.lld.library.services;

import java.util.EnumMap;
import java.util.Map;

import com.lld.library.Member;
import com.lld.library.enums.NotificationChannel;
import com.lld.library.notifier.Notifier;

public class NotificationService {
    private final Map<NotificationChannel, Notifier> map = new EnumMap<>(NotificationChannel.class);

    public void registerNotifier(NotificationChannel channel, Notifier notifier) {
        this.map.put(channel, notifier);
    }

    public void notifyMember(Member member, String message) {
        Notifier notifier = this.map.get(member.getNotificationPreference());

        if (notifier == null) {
            throw new IllegalStateException("No registered preference available for the member");
        }

        notifier.notify(member, message);
    }
}
