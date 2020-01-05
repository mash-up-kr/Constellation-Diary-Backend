package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundAuthenticationPurposeException extends BaseException {
    public NotFoundAuthenticationPurposeException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundAuthenticationPurposeException(HttpStatus httpStatus) {
        this(4002, httpStatus);
    }

    private NotFoundAuthenticationPurposeException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Authentication Purpose")
                .build());
    }
}
