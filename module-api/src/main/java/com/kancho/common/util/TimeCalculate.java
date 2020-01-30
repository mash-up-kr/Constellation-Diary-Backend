package com.kancho.common.util;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.exception.RequestWrongFieldException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class TimeCalculate {

    private final static Integer ZERO = 0;
    private final static Integer TWENTY_TWO = 22;
    private final static Integer EIGHT = 8;
    private final static Integer FIFTY_NINE = 59;
    private final static Long LONG_ONE = 1L;
    private final static Long DAY_TIME = 24L;
    private final static Integer ONE_DAY = 1;
    private final static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

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

    public static Date convertDate(String date) {
        SimpleDateFormat transFormat = new SimpleDateFormat(DATE_FORMAT);
        Date to = null;
        try {
            to = transFormat.parse(date);
        } catch (ParseException e) {
            throw new RequestWrongFieldException();
        }
        return to;
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

    public static LocalTime createQuestionTime(ReqTimeZone reqTimeZone) {
        LocalTime questionTime = LocalTime.of(TWENTY_TWO, ZERO);
        return questionTime.minusHours(reqTimeZone.getParallax());
    }

    public static LocalTime createHoroscopeTime(ReqTimeZone reqTimeZone) {
        LocalTime horoscopeTime = LocalTime.of(EIGHT, ZERO);
        return horoscopeTime.minusHours(reqTimeZone.getParallax());
    }


    private TimeCalculate() {

    }


}
