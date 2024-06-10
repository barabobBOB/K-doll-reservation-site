package com.rena.ticket.member.service;

import com.rena.ticket.global.exception.MemberException;
import com.rena.ticket.member.domain.Member;
import com.rena.ticket.member.domain.repository.MemberRepository;
import com.rena.ticket.member.domain.type.MemberAccessType;
import com.rena.ticket.member.dto.request.MemberCreateRequest;
import com.rena.ticket.member.dto.response.MemberLoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("MemberService 테스트")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @DisplayName("새로운 사용자를 성공적으로 저장한다.")
    @Test
    void testCreateMember() {
        // Given
        MemberCreateRequest request = new MemberCreateRequest(
                "최세연",
                "user@gmail.com",
                "test12345",
                "최세연",
                "010-1234-1234",
                MemberAccessType.ACCESS_TYPE_NORMAL
        );
        given(memberRepository.existsByEmail(request.getEmail())).willReturn(false);

        // When
        memberService.createMember(request);

        // Then
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @DisplayName("이미 존재하는 사용자를 저장하려고 할 때 예외를 던진다.")
    @Test
    void testCreateMemberWithExistingUser() {
        // Given
        MemberCreateRequest request = new MemberCreateRequest(
                "최세연",
                "user@gmail.com",
                "test12345",
                "최세연",
                "010-1234-1234",
                MemberAccessType.ACCESS_TYPE_NORMAL
        );
        given(memberRepository.existsByEmail(request.getEmail())).willReturn(true);

        //When, Then
        assertThrows(MemberException.class, () -> {
            memberService.createMember(request);
        });
    }

    @DisplayName("사용자 ID와 비밀번호로 사용자를 조회한다.")
    @Test
    void testFindIdByEmailAndPassword() {
        // Arrange
        String email = "user@gmail.com";
        String password = "test12345";
        Member member = new Member(
                1L, "최세연", email, password, "최세연", "010-1234-1234", MemberAccessType.ACCESS_TYPE_NORMAL
        );

        // Act
        when(memberRepository.findMemberByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.of(member));
        MemberLoginResponse response = memberService.findIdByEmailAndPassword(email, password);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("최세연", response.getName());
        assertEquals(email, response.getEmail());
        assertEquals("최세연", response.getFullname());
        assertEquals("010-1234-1234", response.getPhoneNumber());
        assertEquals("ACCESS_TYPE_NORMAL", response.getAccessType());

        verify(memberRepository, times(1)).findMemberByEmailAndPassword(email, password);
    }

    @DisplayName("사용자 ID와 비밀번호로 사용자를 조회할 때 사용자를 찾을 수 없으면 예외를 던진다.")
    @Test
    void testFindIdByEmailAndPassword_NotFound() {
        // Arrange
        String email = "notexist@gmail.com";
        String password = "wrongpassword";

        // Act
        when(memberRepository.findMemberByEmailAndPassword(anyString(), anyString())).thenReturn(null);

        // Assert
        assertThrows(NullPointerException.class, () -> {
            memberService.findIdByEmailAndPassword(email, password);
        });
        verify(memberRepository, times(1)).findMemberByEmailAndPassword(email, password);
    }
}