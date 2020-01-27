package com.kancho.byeolbyeol.daily.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
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
                .massage("Is Not The Writer")
                .build());
    }
}