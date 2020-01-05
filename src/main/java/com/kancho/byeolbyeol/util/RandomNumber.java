package com.kancho.byeolbyeol.util;

import java.util.Random;

public class RandomNumber {

    private final static  Integer MIN = 100000;
    private final static  Integer MAX = 999999;

    public static Integer generateNumber() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return (random.nextInt() * MAX) * MIN;
    }
}