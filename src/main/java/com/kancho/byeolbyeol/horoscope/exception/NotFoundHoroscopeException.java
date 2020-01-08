package com.kancho.byeolbyeol.horoscope.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundHoroscopeException extends BaseException {
    public NotFoundHoroscopeException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private NotFoundHoroscopeException(HttpStatus httpStatus) {
        this(4009, httpStatus);
    }

    private NotFoundHoroscopeException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Horoscope")
                .build());
    }
}