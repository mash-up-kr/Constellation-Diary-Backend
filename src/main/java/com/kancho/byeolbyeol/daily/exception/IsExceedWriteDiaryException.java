package com.kancho.byeolbyeol.daily.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
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
                .massage("Is Exceed Write Diary")
                .build());
    }
}