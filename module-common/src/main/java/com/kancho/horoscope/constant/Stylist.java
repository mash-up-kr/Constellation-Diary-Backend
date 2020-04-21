package com.kancho.horoscope.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Stylist {
    METAL_MATERIAL_COMPONENTS("금속"),
    SILVER_RING("은반지"),
    BOOK("책"),
    BOTTLE_OF_WATER("물병"),
    LEATHER_BAGS("가죽가방"),
    WALLET("지갑"),
    SCARF("스카프"),
    GLASSES("안경"),
    WOOD_PROPS("나무소품"),
    NECKLACE("목걸이"),
    RAW_FLOWERS("생화"),
    BADGE("뱃지"),
    PEARL("진주"),
    BELT("벨트");

    private String value;
}
