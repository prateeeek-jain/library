package com.lld.library.notifier;

import com.lld.library.Member;

public class SmsNotifier implements Notifier {

    @Override
    public void notify(Member member, String message) {
        System.out.println("SMS notification sent to: " + member.getName() + " with message: " + message);
    }

}
