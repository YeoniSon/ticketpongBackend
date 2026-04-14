package com.ticketpong.member.domain.members;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("USER")
public class GeneralMember extends Member {
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String detailAddress;
}
