package com.kancho.byeolbyeol.horoscope.domain.horoscope.constant;

import lombok.Getter;

@Getter
public enum Stylist {
    METAL_MATERIAL_COMPONENTS("금속재료소품"),
    SILVER_RING("은색반지"),
    BOOK("책"),
    BOTTLE_OF_WATER("물이 담긴 병"),
    LEATHER_BAGS("가죽가방"),
    WALLET("지갑"),
    SCARF("스카프"),
    GLASSES("안경"),
    WOOD_PROPS("나무로 된 소품"),
    NECKLACE("목걸이"),
    RAW_FLOWERS("생화"),
    BADGE("뱃지"),
    PEARL("진주"),
    BELT("벨트");

    private String value;

    Stylist(String value) {
        this.value = value;
    }
}
