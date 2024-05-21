package com.rena.ticket.global.exception;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {
    private final int code;
    private final String message;

    public MemberException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
