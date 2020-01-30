package com.kancho.user.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotSelectConstellationException extends BaseException {
    public NotSelectConstellationException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotSelectConstellationException(HttpStatus httpStatus) {
        this(4004, httpStatus);
    }

    private NotSelectConstellationException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Select Constellation")
                .build());
    }
}