package com.kancho.byeolbyeol.common.util;

import com.kancho.byeolbyeol.common.constant.ReqTimeZone;

import java.time.*;
import java.util.Date;

public class TimeCalculate {

    private final static Integer ZERO = 0;
    private final static Integer FIFTY_NINE = 59;
    private final static Long LONG_ONE = 1L;
    private final static Long DAY_TIME = 24L;
    private final static Integer ONE_DAY = 1;

    public static LocalDate covertLocalDate(LocalDateTime nowTime, ReqTimeZone reqTimeZone) {
        return nowTime.plusHours(reqTimeZone.getParallax()).toLocalDate();
    }

    public static Date covertDate(LocalDateTime nowTime, ReqTimeZone reqTimeZone) {
        return Date.from(nowTime.plusHours(reqTimeZone.getParallax()).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalTime convertLocalTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
    }

    public static LocalDateTime covertLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime createStartTime(LocalDateTime localDateTime, ReqTimeZone reqTimeZone) {
        LocalDateTime localDateKstTime;
        if (localDateTime.getHour() >= (DAY_TIME - reqTimeZone.getParallax())) {
            localDateKstTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                    localDateTime.getDayOfMonth(), (int) (DAY_TIME - reqTimeZone.getParallax()), ZERO, ZERO);

        } else {
            localDateKstTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                    localDateTime.getDayOfMonth() - ONE_DAY, (int) (DAY_TIME - reqTimeZone.getParallax()), ZERO, ZERO);
        }
        return localDateKstTime;
    }

    public static LocalDateTime createEndTime(LocalDateTime localDateTime, ReqTimeZone reqTimeZone) {
        LocalDateTime localDateKstTime;
        if (localDateTime.getHour() >= (DAY_TIME - reqTimeZone.getParallax())) {
            localDateKstTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                    localDateTime.getDayOfMonth() + ONE_DAY, (int) (DAY_TIME - reqTimeZone.getParallax()), FIFTY_NINE, FIFTY_NINE);

        } else {
            localDateKstTime = LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                    localDateTime.getDayOfMonth(), (int) (DAY_TIME - reqTimeZone.getParallax()), FIFTY_NINE, FIFTY_NINE);
        }
        return localDateKstTime;
    }

    public static Long getDeadLine(ReqTimeZone reqTimeZone) {
        return DAY_TIME - reqTimeZone.getParallax();
    }

    public static LocalDateTime createStartDate(Integer year, Integer month, ReqTimeZone reqTimeZone) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, ONE_DAY, ZERO, ZERO, ZERO);
        return localDateTime.minusDays(LONG_ONE).minusHours(reqTimeZone.getParallax());
    }

    public static LocalDateTime createEndDate(Integer year, Integer month, ReqTimeZone reqTimeZone) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, ONE_DAY, ZERO, ZERO, ZERO);
        return localDateTime.plusMonths(LONG_ONE).minusHours(reqTimeZone.getParallax());
    }

    private TimeCalculate() {

    }


}
