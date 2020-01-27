package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsNotExistsUserException extends BaseException {
    public IsNotExistsUserException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private IsNotExistsUserException(HttpStatus httpStatus) {
        this(4002, httpStatus);
    }

    private IsNotExistsUserException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Is Not Exists User - check UserId or Email")
                .build());
    }
}
