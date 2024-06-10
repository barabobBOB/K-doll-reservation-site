package com.rena.ticket.member.service;

import com.rena.ticket.global.exception.ExceptionCode;
import com.rena.ticket.global.exception.MemberException;
import com.rena.ticket.member.domain.Member;
import com.rena.ticket.member.domain.repository.MemberRepository;
import com.rena.ticket.member.dto.request.MemberCreateRequest;
import com.rena.ticket.member.dto.response.MemberLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public void createMember(MemberCreateRequest memberCreateRequest) {
        if (Boolean.TRUE.equals(memberRepository.existsByEmail(memberCreateRequest.getEmail()))) {
            throw new MemberException(ExceptionCode.MEMBER_DUPLICATED_EMAIL);
        }
        memberRepository.save(Member.of(memberCreateRequest));
    }

    public MemberLoginResponse findIdByEmailAndPassword(String email, String password) {
        Optional<Member> member = memberRepository.findMemberByEmailAndPassword(email, password);
        return member.map(
                value -> MemberLoginResponse.of(
                        value.getId(),
                        value.getName(),
                        value.getEmail(),
                        value.getFullname(),
                        value.getPhoneNumber(),
                        value.getAccessType().name()))
                .orElseThrow(() -> new MemberException(ExceptionCode.MEMBER_NOT_FOUND));
    }
}
