package com.kancho.byeolbyeol.home.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundQuestionException extends BaseException {
    public NotFoundQuestionException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundQuestionException(HttpStatus httpStatus) {
        this(4008, httpStatus);
    }

    private NotFoundQuestionException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Question")
                .build());
    }
}
