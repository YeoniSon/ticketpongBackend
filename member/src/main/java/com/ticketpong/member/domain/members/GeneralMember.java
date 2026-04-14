package com.ticketpong.member.domain.members;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@DiscriminatorValue("USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeneralMember extends Member {
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)   
    private String detailAddress;
}
