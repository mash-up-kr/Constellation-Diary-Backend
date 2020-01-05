package com.kancho.byeolbyeol.authentication;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
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