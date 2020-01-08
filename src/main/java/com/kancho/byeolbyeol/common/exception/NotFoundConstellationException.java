package com.kancho.byeolbyeol.common.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundConstellationException extends BaseException {
    public NotFoundConstellationException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundConstellationException(HttpStatus httpStatus) {
        this(4004, httpStatus);
    }

    private NotFoundConstellationException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Constellation")
                .build());
    }
}
