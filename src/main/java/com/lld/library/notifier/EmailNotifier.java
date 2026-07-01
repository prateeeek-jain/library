package com.lld.library.notifier;

import com.lld.library.Member;

public class EmailNotifier implements Notifier {

    @Override
    public void notify(Member member, String message) {
        System.out.println("EMAIL notification sent to: " + member.getName() + " with message: " + message);
    }

}
