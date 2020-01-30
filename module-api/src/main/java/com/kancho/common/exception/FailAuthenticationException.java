package com.kancho.common.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class FailAuthenticationException extends BaseException {

    public FailAuthenticationException() {
        this(HttpStatus.UNAUTHORIZED);
    }

    private FailAuthenticationException(HttpStatus httpStatus) {
        this(4101, httpStatus);
    }

    private FailAuthenticationException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Fail Authentication - check token")
                .build());
    }
}