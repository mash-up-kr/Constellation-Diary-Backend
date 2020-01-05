package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BaseException {
    public NotFoundUserException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundUserException(HttpStatus httpStatus) {
        this(4006, httpStatus);
    }

    private NotFoundUserException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found User")
                .build());
    }
}
