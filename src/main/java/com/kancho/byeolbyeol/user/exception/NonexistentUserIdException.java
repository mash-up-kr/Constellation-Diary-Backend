package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NonexistentUserIdException extends BaseException {
    public NonexistentUserIdException() {
        this(HttpStatus.UNAUTHORIZED);
    }

    private NonexistentUserIdException(HttpStatus httpStatus) {
        this(4104, httpStatus);
    }

    private NonexistentUserIdException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Nonexistent UserId")
                .build());
    }
}
