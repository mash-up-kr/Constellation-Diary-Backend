package com.kancho.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorModel {
    private int code;
    private HttpStatus httpStatus;
    private String message;

    @Builder
    private ErrorModel(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
