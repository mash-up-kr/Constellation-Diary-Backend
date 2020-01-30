package com.kancho.common.util;

public class ReplacePassword {

    public static String changeAsterisk(String id) {
        StringBuilder result = new StringBuilder();
        String frontId;
        int size;

        if (id.length() <= 4) {
            frontId = id.substring(0, 2);
            size = id.substring(2).length();
        } else {
            frontId = id.substring(0, 4);
            size = id.substring(4).length();
        }

        result.append(frontId);
        for (int i = 0; i < size; i++) {
            result.append("*");
        }

        return result.toString();
    }

    private ReplacePassword() {

    }
}
