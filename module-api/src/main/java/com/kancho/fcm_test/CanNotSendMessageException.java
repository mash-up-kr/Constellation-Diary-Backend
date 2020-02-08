package com.kancho.fcm_test;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class CanNotSendMessageException extends BaseException {
    public CanNotSendMessageException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private CanNotSendMessageException(HttpStatus httpStatus) {
        this(5001, httpStatus);
    }

    private CanNotSendMessageException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Fcm Send Error")
                .build());
    }
}

