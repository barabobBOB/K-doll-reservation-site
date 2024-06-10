package com.rena.ticket.global.exception;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {
    private final int code;
    private final String message;

    public MemberException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
