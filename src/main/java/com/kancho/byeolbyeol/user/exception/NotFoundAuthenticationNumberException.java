package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundAuthenticationNumberException extends BaseException {
    public NotFoundAuthenticationNumberException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundAuthenticationNumberException(HttpStatus httpStatus) {
        this(4002, httpStatus);
    }

    private NotFoundAuthenticationNumberException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Authentication-Number")
                .build());
    }
}
