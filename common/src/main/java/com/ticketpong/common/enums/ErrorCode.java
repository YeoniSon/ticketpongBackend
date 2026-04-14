package com.ticketpong.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    // 회원가입 에러
    ALREADY_EXISTS_ID(HttpStatus.CONFLICT, "이미 존재하는 아이디 입니다."),
    ALREADY_EXISTS_PHONE(HttpStatus.CONFLICT, "이미 존재하는 전화번호 입니다."),
    ALREADY_EXISTS_EMAIL(HttpStatus.CONFLICT, "이미 존재하는 이메일 입니다."),

    // token
    NOT_FOUND_TOKEN(HttpStatus.NOT_FOUND, "토큰이 존재하지 않습니다."),
    INVALID_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "토큰이 타입이 일치하지 않습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.message = msg;
    }

    public int getStatus() {
        return httpStatus.value();
    }
}
