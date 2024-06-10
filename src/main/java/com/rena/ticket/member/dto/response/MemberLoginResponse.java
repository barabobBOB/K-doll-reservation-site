package com.rena.ticket.member.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberLoginResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String fullname;
    private final String phoneNumber;
    private final String accessType;

    public static MemberLoginResponse of(Long id, String name, String email, String fullname, String phoneNumber, String accessType) {
        return new MemberLoginResponse(id, name, email, fullname, phoneNumber, accessType);
    }
}
