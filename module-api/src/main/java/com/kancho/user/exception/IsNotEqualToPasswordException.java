package com.kancho.user.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsNotEqualToPasswordException extends BaseException {
    public IsNotEqualToPasswordException() {
        this(HttpStatus.UNAUTHORIZED);
    }

    private IsNotEqualToPasswordException(HttpStatus httpStatus) {
        this(4105, httpStatus);
    }

    private IsNotEqualToPasswordException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .message("Is Not Equal To Password")
                .build());
    }
}