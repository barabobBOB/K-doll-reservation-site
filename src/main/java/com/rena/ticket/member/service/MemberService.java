package com.rena.ticket.member.service;

import com.rena.ticket.member.domain.Member;
import com.rena.ticket.member.domain.repository.MemberRepository;
import com.rena.ticket.member.dto.request.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public void createMember(MemberCreateRequest memberCreateRequest) {
        memberRepository.save(Member.of(memberCreateRequest));
    }
}
