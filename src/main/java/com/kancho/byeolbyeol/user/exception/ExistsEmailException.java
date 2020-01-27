package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
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
                .massage("Exists Email")
                .build());
    }
}
