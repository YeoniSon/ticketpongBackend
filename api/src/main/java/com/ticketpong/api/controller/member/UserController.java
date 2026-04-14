package com.ticketpong.api.controller.member;

import com.ticketpong.member.dto.request.signup.SignUpUserRequest;
import com.ticketpong.member.dto.response.signup.CheckIdResponse;
import com.ticketpong.member.dto.response.signup.SignUpResponse;
import com.ticketpong.member.service.EmailVerificationService;
import com.ticketpong.member.service.SignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@Tag(name = "회원", description = "회원가입, 로그인, 프로필 조회, 수정, 비밀번호 변경, 탈퇴")
@RequiredArgsConstructor
public class UserController {

    private final SignUpService signUpService;
    private final EmailVerificationService emailVerificationService;

    @Operation(summary = "user 회원가입", description = "이메일 인증을 포함한 계정 생성 요청")
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup (@RequestBody SignUpUserRequest request) {
        return ResponseEntity.ok(signUpService.userSignUp(request));
    }

    @Operation(summary = "id 중복 체크", description = "id를 param으로 받아와서 중복확인")
    @PostMapping("/signup/id-check")
    public ResponseEntity<CheckIdResponse> idCheck (@RequestParam String id) {
        return ResponseEntity.ok(signUpService.checkId(id));
    }

    @Operation(summary = "이메일 인증", description = "가입 메일의 토큰으로 이메일 검증 완료")
    @PostMapping("/signup/email-verify")
    public ResponseEntity<String> verifyEmail (@RequestParam String token) {
        emailVerificationService.verifyToken(token);
        return ResponseEntity.ok("이메일 인증 완료");
    }
}
