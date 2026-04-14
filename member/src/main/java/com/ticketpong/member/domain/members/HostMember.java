package com.ticketpong.member.domain.members;

import com.ticketpong.common.enums.ManagePart;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("ADMIN")
public class HostMember extends Member {

    @Enumerated(EnumType.STRING)
    private ManagePart managePart;
}
