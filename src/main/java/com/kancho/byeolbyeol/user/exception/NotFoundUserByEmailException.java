package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundUserByEmailException  extends BaseException {
    public NotFoundUserByEmailException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundUserByEmailException(HttpStatus httpStatus) {
        this(4013, httpStatus);
    }

    private NotFoundUserByEmailException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found User By Email")
                .build());
    }
}