package com.kancho.byeolbyeol.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeCalculate {

    private final static Integer ZERO = 0;
    private final static Integer FIFTY_NINE = 59;
    private final static Long DAY_TIME = 24L;
    private final static Integer ONE_DAY = 1;
    public final static Long KST_NINE = 9L;

    public static LocalDate covertKstLocalDate(LocalDateTime nowTime) {
        return nowTime.plusHours(KST_NINE).toLocalDate();
    }

    public static Date covertDate(LocalDateTime nowTime) {
        return Date.from(nowTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime createStartKstTime(LocalDateTime localDateTime) {
        LocalDateTime localDateKstTime;
        if (localDateTime.getHour() >= (DAY_TIME - KST_NINE)) {
            localDateKstTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                    localDateTime.getDayOfMonth(), (int) (DAY_TIME - KST_NINE), ZERO, ZERO);

        } else {
            localDateKstTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                    localDateTime.getDayOfMonth() - ONE_DAY, (int) (DAY_TIME - KST_NINE), ZERO, ZERO);
        }
        return localDateKstTime;
    }

    public static LocalDateTime createEndKstTime(LocalDateTime localDateTime) {
        LocalDateTime localDateKstTime;
        if (localDateTime.getHour() >= (DAY_TIME - KST_NINE)) {
            localDateKstTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                    localDateTime.getDayOfMonth() + ONE_DAY, (int) (DAY_TIME - KST_NINE), FIFTY_NINE, FIFTY_NINE);

        } else {
            localDateKstTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                    localDateTime.getDayOfMonth(), (int) (DAY_TIME - KST_NINE), FIFTY_NINE, FIFTY_NINE);
        }
        return localDateKstTime;
    }

    public static Long getKstDeadLine() {
        return DAY_TIME - KST_NINE;
    }

    private TimeCalculate() {

    }


}
