package com.ticketpong.member.dto.request.signup;

import com.ticketpong.common.enums.ManagePart;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SignUpAdminRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    @Past
    private LocalDate birth;

    @NotBlank
    private ManagePart managePart;
}
