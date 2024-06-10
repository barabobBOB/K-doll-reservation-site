package com.rena.ticket.member.domain.repository;

import com.rena.ticket.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer>{
     Boolean existsByEmail(String email);
     Optional<Member> findMemberByEmailAndPassword(String email, String password);
}
