package com.ticketpong.member.repository;

import com.ticketpong.member.domain.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<Authentication, Long> {
    Optional<Authentication> findByToken(String token);
}
