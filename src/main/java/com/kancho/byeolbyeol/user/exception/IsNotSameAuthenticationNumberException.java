package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsNotSameAuthenticationNumberException extends BaseException {
    public IsNotSameAuthenticationNumberException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private IsNotSameAuthenticationNumberException(HttpStatus httpStatus) {
        this(4003, httpStatus);
    }

    private IsNotSameAuthenticationNumberException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Is Not Same Authentication-Number")
                .build());
    }
}