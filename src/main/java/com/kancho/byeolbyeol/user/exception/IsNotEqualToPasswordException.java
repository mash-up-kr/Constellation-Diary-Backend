package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsNotEqualToPasswordException extends BaseException {
    public IsNotEqualToPasswordException() {
        this(HttpStatus.UNAUTHORIZED);
    }

    private IsNotEqualToPasswordException(HttpStatus httpStatus) {
        this(4015, httpStatus);
    }

    private IsNotEqualToPasswordException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("check password")
                .build());
    }
}
