package com.lld.library.services;

import java.util.*;

import com.lld.library.Member;

public class MemberService {
    private Set<String> members;

    public MemberService() {
        this.members = new HashSet<>();
    }

    public boolean checkMember(String memberId) {
        return this.members.contains(memberId);
    }

    public Member addNewMember(Member member) {
        this.members.add(member.getId());

        return member;
    }
}
