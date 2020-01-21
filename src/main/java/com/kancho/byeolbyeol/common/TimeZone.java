package com.kancho.byeolbyeol.common;

import lombok.Getter;

@Getter
public enum TimeZone {
    UTC("UTC"),
    KST("KST");

    private String value;


    TimeZone(String value) {
        this.value = value;
    }
}
