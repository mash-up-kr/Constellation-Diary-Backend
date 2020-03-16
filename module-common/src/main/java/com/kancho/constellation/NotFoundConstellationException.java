package com.kancho.constellation;

import lombok.Getter;

@Getter
public class NotFoundConstellationException extends RuntimeException {
    private String message;

    public NotFoundConstellationException() {
       this.message = "Not Found Constellation";
    }
}
