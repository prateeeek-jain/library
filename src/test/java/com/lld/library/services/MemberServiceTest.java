package com.lld.library.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lld.library.Member;
import com.lld.library.enums.MemberShipType;

class MemberServiceTest {

    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService();
    }

    private Member member(String id) {
        return new Member(id, "Alice", "alice@example.com", MemberShipType.REGULAR);
    }

    @Test
    void checkMember_onEmptyService_returnsFalse() {
        assertFalse(memberService.checkMember("M1"));
    }

    @Test
    void addNewMember_returnsTheSameInstance() {
        Member m = member("M1");

        assertSame(m, memberService.addNewMember(m));
    }

    @Test
    void checkMember_afterAdd_returnsTrue() {
        memberService.addNewMember(member("M1"));

        assertTrue(memberService.checkMember("M1"));
    }

    @Test
    void checkMember_unknownId_returnsFalse() {
        memberService.addNewMember(member("M1"));

        assertFalse(memberService.checkMember("M2"));
    }

    @Test
    void addNewMember_sameIdTwice_staysRegisteredOnce() {
        memberService.addNewMember(member("M1"));
        // A different instance carrying the same id must not create a second
        // registration.
        memberService.addNewMember(member("M1"));

        assertTrue(memberService.checkMember("M1"));
    }

    @Test
    void membership_isTrackedById_notByObjectIdentity() {
        // Storing ids (not Member objects) sidesteps Member's broken equals/hashCode:
        // any instance carrying a registered id resolves correctly.
        memberService.addNewMember(member("M1"));

        assertTrue(memberService.checkMember(member("M1").getId()));
    }
}
