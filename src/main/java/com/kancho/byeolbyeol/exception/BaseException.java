package com.kancho.byeolbyeol.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private ErrorModel errorModel;

    protected BaseException(ErrorModel errorModel) {
        super(errorModel.getMassage());
        this.errorModel = errorModel;
    }
}