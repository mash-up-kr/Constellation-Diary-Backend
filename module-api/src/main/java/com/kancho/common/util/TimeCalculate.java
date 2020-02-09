package com.kancho.common.util;

import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.exception.RequestWrongFieldException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeCalculate {

    private final static Integer ZERO = 0;
    private final static Integer ONE = 1;
    private final static Integer TWELVE = 12;
    private final static Integer TWENTY_TWO = 22;
    private final static Integer EIGHT = 8;
    private final static Integer FIFTY_NINE = 59;
    private final static Long LONG_ONE = 1L;
    private final static Long DAY_TIME = 24L;
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
        Date result;
        try {
            result = transFormat.parse(date);
        } catch (ParseException e) {
            throw new RequestWrongFieldException();
        }
        return result;
    }

    public static LocalDateTime createStartTime(LocalDateTime localDateTime, ReqTimeZone reqTimeZone) {
        LocalDateTime temp = copyLocalDateTime(localDateTime);

        int year = localDateTime.getYear();
        Month month = localDateTime.getMonth();
        int day = localDateTime.getDayOfMonth();
        temp = temp.minusDays(ONE);

        if (isNotSameMonth(temp, localDateTime)) {
            year = temp.getYear();
            month = temp.getMonth();
            day = temp.getDayOfMonth();
        }

        return LocalDateTime.of(year, month, day, (int) (DAY_TIME - reqTimeZone.getParallax()), ZERO, ZERO);

    }

    public static LocalDateTime createEndTime(LocalDateTime localDateTime, ReqTimeZone reqTimeZone) {
        LocalDateTime temp = copyLocalDateTime(localDateTime);

        int year = localDateTime.getYear();
        Month month = localDateTime.getMonth();
        int day = localDateTime.getDayOfMonth();
        temp = temp.plusDays(ONE);

        if (isNotSameMonth(temp, localDateTime)) {
            year = temp.getYear();
            month = temp.getMonth();
            day = temp.getDayOfMonth();
        }

        return LocalDateTime.of(year, month, day, (int) (DAY_TIME - reqTimeZone.getParallax()), FIFTY_NINE, FIFTY_NINE);
    }

    public static List<MonthRange> createRangeMonth(Integer year, ReqTimeZone reqTimeZone) {
        List<MonthRange> months = new ArrayList<>();

        for (int i = ONE; i <= TWELVE; i++) {
            LocalDateTime startDateTime = LocalDateTime.of(year, i, ONE, ZERO, ZERO, ZERO);
            startDateTime = startDateTime.minusHours(reqTimeZone.getParallax());

            LocalDateTime endDateTime = LocalDateTime.of(year, i, ONE, ZERO, ZERO, ZERO);
            endDateTime = endDateTime.plusMonths(LONG_ONE).minusHours(reqTimeZone.getParallax());

            MonthRange monthRange = new MonthRange(startDateTime, endDateTime);

            months.add(monthRange);
        }
        return months;
    }

    public static Long getDeadLine(ReqTimeZone reqTimeZone) {
        return DAY_TIME - reqTimeZone.getParallax();
    }

    public static LocalDateTime createStartDate(Integer year, Integer month, ReqTimeZone reqTimeZone) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, ONE, ZERO, ZERO, ZERO);
        return localDateTime.minusHours(reqTimeZone.getParallax());
    }

    public static LocalDateTime createEndDate(Integer year, Integer month, ReqTimeZone reqTimeZone) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, ONE, ZERO, ZERO, ZERO);
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

    private static LocalDateTime copyLocalDateTime(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(),
                localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());

    }

    private static boolean isNotSameMonth(LocalDateTime left, LocalDateTime right) {
        return !(left.getMonth().getValue() == right.getMonth().getValue());
    }

}
