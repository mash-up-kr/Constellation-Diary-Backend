package com.kancho.byeolbyeol.common.exception;

import com.kancho.byeolbyeol.exception.BaseException;
import com.kancho.byeolbyeol.exception.ErrorModel;
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
                .massage("Request Field Null or Wrong Form - Check Request Field")
                .build());
    }
}
