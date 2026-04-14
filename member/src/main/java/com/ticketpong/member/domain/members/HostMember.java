package com.ticketpong.member.domain.members;

import com.ticketpong.common.enums.ManagePart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@DiscriminatorValue("ADMIN")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HostMember extends Member {

    @Enumerated(EnumType.STRING)
    private ManagePart managePart;
}
