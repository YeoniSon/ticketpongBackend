package com.ticketpong.member.dto.response.signup;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckIdResponse {
    private boolean available;
}
