package com.rena.ticket.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
    INVALID_REQUEST(400, "Invalid Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    MEMBER_DUPLICATED_EMAIL(400, "Member Duplicated Email"),
    MEMBER_NOT_FOUND(404, "Member Not Found");

    private final int code;
    private final String message;
}
