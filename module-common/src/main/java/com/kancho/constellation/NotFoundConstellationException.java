package com.kancho.constellation;



public class NotFoundConstellationException extends RuntimeException {
    private String massage;

    public NotFoundConstellationException() {
       this.massage = "Not Found Constellation";
    }
}
