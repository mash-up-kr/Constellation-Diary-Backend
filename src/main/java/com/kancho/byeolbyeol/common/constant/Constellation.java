package com.kancho.byeolbyeol.common.constant;

import com.kancho.byeolbyeol.common.exception.NotFoundConstellationException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Constellation {
    CAPRICORN("염소자리"),
    AQUARIUS("물병자리"),
    PISCES("물고기자리"),
    ARIES("양자리"),
    TAURUS("황소자리"),
    GEMINI("쌍둥이자리"),
    CANCER("게자리"),
    LEO("사자자리"),
    VIRGO("처녀자리"),
    LIBRA("천칭자리"),
    SCORPIO("전갈자리"),
    SAGITTARIUS("궁수자리");

    private String value;

    public static Constellation findByConstellation(String value) {
        return Arrays.stream(Constellation.values())
                .filter(constellation -> constellation.hasValue(value))
                .findAny()
                .orElseThrow(NotFoundConstellationException::new);
    }

    private boolean hasValue(String value) {
        return this.value.equals(value);
    }
}
