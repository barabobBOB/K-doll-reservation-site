package com.rena.ticket.member.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rena.ticket.member.domain.type.MemberAccessType;
import com.rena.ticket.member.dto.request.MemberCreateRequest;
import com.rena.ticket.member.dto.request.MemberLoginRequest;
import com.rena.ticket.member.dto.response.MemberLoginResponse;
import com.rena.ticket.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("회원 Controller 테스트")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @DisplayName("회원 가입 테스트")
    @Test
    void testRegisterMember() throws Exception {
        // Given
        MemberCreateRequest request = new MemberCreateRequest(
                "최세연",
                "user@gmail.com",
                "test12345",
                "최세연",
                "010-1234-1234",
                MemberAccessType.ACCESS_TYPE_NORMAL
        );

        // When & Then
        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @DisplayName("로그인 테스트")
    @Test
    void testLogin() throws Exception {
        // Given
        MemberLoginRequest request = new MemberLoginRequest("user@gmail.com", "test12345");
        MemberLoginResponse response = new MemberLoginResponse(
                1L,
                "최세연",
                "user@gmail.com",
                "최세연",
                "010-1234-1234",
                "ACCESS_TYPE_NORMAL"
        );

        when(memberService.findIdByEmailAndPassword(any(String.class), any(String.class))).thenReturn(response);

        // When & Then
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("최세연"))
                .andExpect(jsonPath("$.email").value("user@gmail.com"))
                .andExpect(jsonPath("$.fullname").value("최세연"))
                .andExpect(jsonPath("$.phoneNumber").value("010-1234-1234"))
                .andExpect(jsonPath("$.accessType").value("ACCESS_TYPE_NORMAL"));
    }

}