package com.kancho.byeolbyeol.diary.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class IsExceedWriteDiaryException extends BaseException {
    public IsExceedWriteDiaryException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private IsExceedWriteDiaryException(HttpStatus httpStatus) {
        this(4012, httpStatus);
    }

    private IsExceedWriteDiaryException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Is Exceed Write Diary")
                .build());
    }
}