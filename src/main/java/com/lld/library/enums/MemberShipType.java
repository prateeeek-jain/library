package com.lld.library.enums;

import com.lld.library.Member;

public enum MemberShipType {
    REGULAR(3, 14, 5) {
        @Override
        public int calculateAmount(int days) {
            return Math.max(days - 14, 0) * 5;
        }

        @Override
        public String welcomeMessage(Member member) {
            return "Welcome to Premium " + member.getName() + "! Enjoy your extended privileges.";
        }
    },

    PREMIUM(10, 30, 2) {
        @Override
        public int calculateAmount(int days) {
            return Math.max(days - 30, 0) * 2;
        }

        @Override
        public String welcomeMessage(Member member) {
            return "Welcome " + member.getName();
        }
    };

    private final int bookCount;
    private final int borrowDays;
    private final int perDayFine;

    MemberShipType(int bookCount, int borrowDays, int perDayFine) {
        this.bookCount = bookCount;
        this.borrowDays = borrowDays;
        this.perDayFine = perDayFine;
    }

    public int getBookBorrowCount() {
        return this.bookCount;
    }

    public int getBorrowDays() {
        return this.borrowDays;
    }

    public int getperDayFine() {
        return this.perDayFine;
    }

    public abstract int calculateAmount(int days);

    public abstract String welcomeMessage(Member member);
}
