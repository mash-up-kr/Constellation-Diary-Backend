package com.kancho.common.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReqTimeZone {
    UTC("UTC", 0L),
    KST("KST", 9L);

    private String value;
    private Long parallax;
}
