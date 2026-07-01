package com.lld.library.notifier;

import com.lld.library.Member;

public class WhatsappNotifier implements Notifier {

    @Override
    public void notify(Member member, String message) {
        System.out.println("Whatsapp notification sent to: " + member.getName() + " with message: " + message);
    }

}
