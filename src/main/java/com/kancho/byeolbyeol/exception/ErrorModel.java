package com.kancho.byeolbyeol.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorModel {
    private int code;
    private HttpStatus httpStatus;
    private String massage;

    @Builder
    private ErrorModel(int code, HttpStatus httpStatus, String massage) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.massage = massage;
    }
}
