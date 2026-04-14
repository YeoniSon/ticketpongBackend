package com.ticketpong.member.domain.members;

import com.ticketpong.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") // DB에 dtype 컬럼 생성
@Table(name = "member")
public abstract class Member extends BaseTimeEntity {
    @Id
    @Column(length = 50, nullable = false, updatable = false, unique = true)
    @Setter
    private String id;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    private Boolean auth = false;
}
