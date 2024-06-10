package com.rena.ticket.member.dto.request;

import com.rena.ticket.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateRequest {
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String fullname;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "접근 권한을 입력해주세요.")
    private Member.AccessType accessType;
}
