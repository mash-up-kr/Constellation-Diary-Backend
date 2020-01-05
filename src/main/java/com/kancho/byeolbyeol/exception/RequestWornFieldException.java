package com.kancho.byeolbyeol.exception;

import org.springframework.http.HttpStatus;

public class RequestWornFieldException extends BaseException {
    public RequestWornFieldException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private RequestWornFieldException(HttpStatus httpStatus) {
        this(4001, httpStatus);
    }

    private RequestWornFieldException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Request Field Null or Wrong Form - Check Request Field")
                .build());
    }
}
