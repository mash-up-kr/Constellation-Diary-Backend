package com.kancho.byeolbyeol.horoscope.domain.horoscope.constant;

import lombok.Getter;

@Getter
public enum Word {
    LOVE("사랑"),
    FRIENDSHIP("우정"),
    COFFEE("커피"),
    FRIEND("친구"),
    FAMILY("가족"),
    SNACKS("과자"),
    JUICE("쥬스"),
    SINGING("노래"),
    AMUSEMENT_PARK("놀이공원"),
    HAN_RIVER("한강"),
    TUMBLER("텀블러"),
    LAPTOP("노트북"),
    STICKER("스티커");

    private String value;

    Word(String value) {
        this.value = value;
    }

}
