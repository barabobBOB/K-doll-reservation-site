package com.rena.ticket.member.domain.repository;

import com.rena.ticket.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer>{
}
