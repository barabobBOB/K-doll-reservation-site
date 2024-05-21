package com.rena.ticket.member.domain;

import com.rena.ticket.global.util.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 255, nullable = false)
    private String fullname;

    @Column(length = 255, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 255, nullable = false)
    private Access access;

    public enum Access {
        ACCESS_TYPE_NORMAL,
        ACCESS_TYPE_ADMIN,
    }

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
