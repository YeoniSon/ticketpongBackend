package com.ticketpong.member.service;

import com.ticketpong.common.enums.ErrorCode;
import com.ticketpong.common.exception.BusinessException;
import com.ticketpong.member.domain.members.GeneralMember;
import com.ticketpong.member.dto.request.signup.SignUpUserRequest;
import com.ticketpong.member.dto.response.signup.CheckIdResponse;
import com.ticketpong.member.dto.response.signup.SignUpResponse;
import com.ticketpong.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationService emailVerificationService;

    /**
     * 회원가입 구현
     * 1. 아이디 인증 (중복 아이디 있는지 없는 지 확인)
     * 2. 이메일 정보와 폰 번호도 unique인지 확인 필요
     * 3. 비밀번호는 암호화
     * 4. 저장 후 이메일 인증 토큰을 메일로 전송
     * 5. email 인증 후 회원가입 완료
     */

    // id 중복확인
    @Transactional(readOnly = true)
    public CheckIdResponse checkId(String id) {
        boolean exists = memberRepository.existsById(id);
        log.info("id exists: {} : {}", id, exists);
        return CheckIdResponse.builder()
                .available(!exists)
                .build();
    }

    // 회원 회원가입로직
    @Transactional
    public SignUpResponse userSignUp(SignUpUserRequest request) {
        // unique 인증
        boolean exists = validateIdAndEmailAndPhone(request.getId(),request.getEmail(), request.getPhone());

        // member저장
        GeneralMember member = createUser(request);
        memberRepository.save(member);

        String token = emailVerificationService.sendVerificationEmail(member);

        return SignUpResponse.of(request.getId( ),"인증 url을 이메일로 전송했습니다.",token);
    }

    private boolean validateIdAndEmailAndPhone(String id, String email, String phone) {
        if(memberRepository.existsById(id)) {
            throw new BusinessException(ErrorCode.ALREADY_EXISTS_ID);
        }else if(memberRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.ALREADY_EXISTS_EMAIL);
        }else if(memberRepository.existsByPhone(phone)) {
            throw new BusinessException(ErrorCode.ALREADY_EXISTS_PHONE);
        }
        return true;
    }

    private GeneralMember createUser(SignUpUserRequest request) {
        return GeneralMember.builder()
                .id(request.getId())
                .email(request.getEmail())
                .name(request.getName())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .auth(false)
                .birth(request.getBirth())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .build();
    }
}
