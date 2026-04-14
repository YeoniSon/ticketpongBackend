package com.ticketpong.member.dto.response.signup;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpResponse {
    private String id;
    private String token;
    private String message;

    public static SignUpResponse of(String id, String message,String token) {
        return SignUpResponse.builder()
                .id(id)
                .token(token)
                .message(message)
                .build();
    }
}
