package com.kancho.byeolbyeol.common.constant;

import lombok.Getter;

@Getter
public enum ReqTimeZone {
    UTC("UTC", 0L),
    KST("KST", 9L);

    private String value;
    private Long parallax;


    ReqTimeZone(String value, Long parallax) {
        this.value = value;
        this.parallax = parallax;
    }
}
