package com.kancho.byeolbyeol.horoscope.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResHoroscopeDto {

    private Long id;

    private String timeZone;

    private LocalDate date;

    private String content;

    private String stylist;

    private String numeral;

    private String word;

    private String exercise;

    private String constellation;

    @Builder
    private ResHoroscopeDto(Long id, String timeZone, LocalDate date, String content,
                            String stylist, String numeral, String word,
                            String exercise, String constellation) {
        this.id = id;
        this.timeZone = timeZone;
        this.date = date;
        this.content = content;
        this.stylist = stylist;
        this.numeral = numeral;
        this.word = word;
        this.exercise = exercise;
        this.constellation = constellation;
    }
}