package com.ticketpong.member.repository;

import com.ticketpong.member.domain.members.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
