package com.kancho.horoscope.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundHoroscopeException extends BaseException {
    public NotFoundHoroscopeException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundHoroscopeException(HttpStatus httpStatus) {
        this(4007, httpStatus);
    }

    private NotFoundHoroscopeException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .message("Not Found Horoscope")
                .build());
    }
}