package com.kancho.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private ErrorModel errorModel;

    protected BaseException(ErrorModel errorModel) {
        super(errorModel.getMessage());
        this.errorModel = errorModel;
    }
}