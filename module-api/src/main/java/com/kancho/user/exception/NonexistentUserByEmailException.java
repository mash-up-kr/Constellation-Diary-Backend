package com.kancho.user.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NonexistentUserByEmailException extends BaseException {
    public NonexistentUserByEmailException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NonexistentUserByEmailException(HttpStatus httpStatus) {
        this(4003, httpStatus);
    }

    private NonexistentUserByEmailException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Nonexistent User By Email")
                .build());
    }
}