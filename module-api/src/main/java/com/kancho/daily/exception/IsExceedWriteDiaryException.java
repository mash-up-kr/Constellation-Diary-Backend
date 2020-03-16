package com.kancho.daily.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsExceedWriteDiaryException extends BaseException {
    public IsExceedWriteDiaryException() {
        this(HttpStatus.FORBIDDEN);
    }

    private IsExceedWriteDiaryException(HttpStatus httpStatus) {
        this(4302, httpStatus);
    }

    private IsExceedWriteDiaryException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .message("Is Exceed Write Diary")
                .build());
    }
}