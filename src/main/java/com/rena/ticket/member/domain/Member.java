package com.rena.ticket.member.domain;

import com.rena.ticket.global.util.BaseEntity;
import com.rena.ticket.member.dto.request.MemberCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private AccessType accessType;

    public enum AccessType {
        ACCESS_TYPE_NORMAL,
        ACCESS_TYPE_ADMIN,
    }

    public Member(String name, String email, String password, String fullname, String phoneNumber, AccessType accessType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.accessType = accessType;
    }

    public static Member of (final MemberCreateRequest memberCreateRequest) {
        return new Member(
                memberCreateRequest.getName(),
                memberCreateRequest.getEmail(),
                memberCreateRequest.getPassword(),
                memberCreateRequest.getFullname(),
                memberCreateRequest.getPhoneNumber(),
                memberCreateRequest.getAccessType()
        );
    }

}
