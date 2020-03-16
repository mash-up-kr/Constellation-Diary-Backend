package com.kancho.common.exception;

import com.kancho.exception.BaseException;
import com.kancho.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class RequestWrongFieldException extends BaseException {
    public RequestWrongFieldException() {
        this(HttpStatus.BAD_REQUEST);
    }

    private RequestWrongFieldException(HttpStatus httpStatus) {
        this(4001, httpStatus);
    }

    private RequestWrongFieldException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .message("Request Field Null or Wrong Form - Check Request Field")
                .build());
    }
}
