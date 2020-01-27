package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class FailAuthenticationNumberException extends BaseException {
    public FailAuthenticationNumberException() {
        this(HttpStatus.UNAUTHORIZED);
    }

    private FailAuthenticationNumberException(HttpStatus httpStatus) {
        this(4102, httpStatus);
    }

    private FailAuthenticationNumberException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Fail Authentication-Number - check field")
                .build());
    }
}