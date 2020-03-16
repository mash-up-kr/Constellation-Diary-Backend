package com.kancho.daily.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsNotTheWriterException extends BaseException {
    public IsNotTheWriterException() {
        this(HttpStatus.FORBIDDEN);
    }

    private IsNotTheWriterException(HttpStatus httpStatus) {
        this(4301, httpStatus);
    }

    private IsNotTheWriterException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .message("Is Not The Writer")
                .build());
    }
}