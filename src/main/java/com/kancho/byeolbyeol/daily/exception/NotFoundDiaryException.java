package com.kancho.byeolbyeol.daily.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
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
                .massage("Not Found Diary")
                .build());
    }
}

