package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class ExistsUserIdException extends BaseException {
    public ExistsUserIdException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private ExistsUserIdException(HttpStatus httpStatus) {
        this(4006, httpStatus);
    }

    private ExistsUserIdException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Exists UserId")
                .build());
    }
}
