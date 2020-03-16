package com.kancho.daily.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundDiaryException extends BaseException {
    public NotFoundDiaryException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundDiaryException(HttpStatus httpStatus) {
        this(4008, httpStatus);
    }

    private NotFoundDiaryException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .message("Not Found Diary")
                .build());
    }
}

