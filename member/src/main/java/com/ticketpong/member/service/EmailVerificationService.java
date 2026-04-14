package com.ticketpong.member.service;

import com.ticketpong.common.enums.ErrorCode;
import com.ticketpong.common.enums.TokenType;
import com.ticketpong.common.exception.BusinessException;
import com.ticketpong.common.mail.MailService;
import com.ticketpong.member.domain.Authentication;
import com.ticketpong.member.domain.members.Member;
import com.ticketpong.member.repository.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailVerificationService {
    private final AuthTokenRepository authTokenRepository;
    private final MailService mailService;

    @Transactional
    public String sendVerificationEmail(Member member) {
        Authentication token = createEmailVerifyToken(member);
        //token 저장
        authTokenRepository.save(token);

        //email
        String link = "http://localhost:8081/user/signup/email-verify?token=" + token.getToken();

        String subject = "TicketPong의 가입을 환영합니다.";
        String text = "<p>" + member.getName() + "님의 HabitProject 가입을 환영합니다. </p>"
                + "<p><a href=\"" + link + "\"> 인증하기 </a>를 눌러 가입을 완료하세요.</p>";

        mailService.sendMail(member.getEmail(), subject, text);

        //API응답에는 token값만 반환
        return token.getToken();
    }

    //token 인증
    @Transactional
    public void verifyToken(String tokenValue) {
        Authentication token = authTokenRepository.findByToken(tokenValue)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_TOKEN));

        validateToken(token);

        Member member = token.getUser();
        member.verifyEmail();

        // token 인증 후 삭제
        authTokenRepository.delete(token);
    }

    private void validateToken(Authentication token) {
        if(!authTokenRepository.findByToken(token.getToken()).isPresent()) {
            throw new BusinessException(ErrorCode.NOT_FOUND_TOKEN);
        }
        if (token.getType() != TokenType.EMAIL_VERIFICATION) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN_TYPE);
        }

        if(token.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new BusinessException(ErrorCode.EXPIRED_TOKEN);
        }
    }

    // Auth 관련 내용 생성
    private Authentication createEmailVerifyToken(Member member) {
        return Authentication.builder()
                .token(UUID.randomUUID().toString())
                .expiredAt(LocalDateTime.now().plusMinutes(5))
                .type(TokenType.EMAIL_VERIFICATION)
                .user(member)
                .build();
    }


}
