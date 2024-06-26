package com.rena.ticket.member.presentation;

import com.rena.ticket.member.dto.request.MemberCreateRequest;
import com.rena.ticket.member.dto.request.MemberLoginRequest;
import com.rena.ticket.member.dto.response.MemberLoginResponse;
import com.rena.ticket.member.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원", description = "회원 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerMember(
            @RequestBody @Valid MemberCreateRequest memberCreateRequest
    ) {
        memberService.createMember(memberCreateRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(
            @RequestBody @Valid MemberLoginRequest memberCreateRequest
            ) {
        return ResponseEntity.ok(
                memberService.findIdByEmailAndPassword(memberCreateRequest.getEmail(), memberCreateRequest.getPassword())
        );
    }
}
