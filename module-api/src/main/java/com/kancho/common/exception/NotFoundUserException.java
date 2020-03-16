package com.kancho.common.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
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
                .message("Not Found User")
                .build());
    }
}
