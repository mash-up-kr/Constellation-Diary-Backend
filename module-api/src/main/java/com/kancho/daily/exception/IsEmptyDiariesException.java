package com.kancho.daily.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsEmptyDiariesException extends BaseException {
    public IsEmptyDiariesException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private IsEmptyDiariesException(HttpStatus httpStatus) {
        this(4004, httpStatus);
    }

    private IsEmptyDiariesException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .message("Is Empty Diaries")
                .build());
    }
}