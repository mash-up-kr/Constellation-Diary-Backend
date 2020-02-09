package com.kancho.daily.dto.response;

import lombok.Getter;

@Getter
public class ResCountYearDiaryDto {
    private Integer year;

    private Long january;

    private Long february;

    private Long march;

    private Long april;

    private Long may;

    private Long june;

    private Long july;

    private Long august;

    private Long september;

    private Long october;

    private Long november;

    private Long december;

    public ResCountYearDiaryDto(Long january, Long february, Long march,
                                Long april, Long may, Long june,
                                Long july, Long august, Long september,
                                Long october, Long november, Long december) {

        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
    }

    public ResCountYearDiaryDto() {
        this.january = 0L;
        this.february = 0L;
        this.march = 0L;
        this.april = 0L;
        this.may = 0L;
        this.june = 0L;
        this.july = 0L;
        this.august = 0L;
        this.september = 0L;
        this.october = 0L;
        this.november = 0L;
        this.december = 0L;
    }

    public void addInfo(Integer year) {
        this.year = year;
    }
}
