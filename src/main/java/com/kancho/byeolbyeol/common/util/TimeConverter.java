package com.kancho.byeolbyeol.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeConverter {
    private final static Long KST_NINE = 9L;

    public static LocalDate covertKstLocalDate(LocalDateTime nowTime) {
        return nowTime.plusHours(KST_NINE).toLocalDate();
    }

    public static Date covertDate(LocalDateTime nowTime) {
        return Date.from(nowTime.toLocalDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date covertKstToUctDate(LocalDate nowTime) {
        return Date.from(nowTime.atStartOfDay().minusHours(KST_NINE).atZone(ZoneId.systemDefault()).toInstant());
    }

    private TimeConverter() {

    }
}
