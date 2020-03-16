package com.kancho.user.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class ExistsEmailException extends BaseException {
    public ExistsEmailException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private ExistsEmailException(HttpStatus httpStatus) {
        this(4009, httpStatus);
    }

    private ExistsEmailException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .message("Exists Email")
                .build());
    }
}
