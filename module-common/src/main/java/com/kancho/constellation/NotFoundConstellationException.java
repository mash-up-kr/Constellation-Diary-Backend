package com.kancho.constellation;

import lombok.Getter;

@Getter
public class NotFoundConstellationException extends RuntimeException {
    private String massage;

    public NotFoundConstellationException() {
       this.massage = "Not Found Constellation";
    }
}
