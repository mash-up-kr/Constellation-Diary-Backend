package com.kancho.byeolbyeol.user.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundConstellationException extends BaseException {
    public NotFoundConstellationException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundConstellationException(HttpStatus httpStatus) {
        this(4003, httpStatus);
    }

    private NotFoundConstellationException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Constellation")
                .build());
    }
}
