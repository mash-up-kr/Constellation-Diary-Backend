package com.kancho.daily.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResSimpleDiaryDto {

    private Long id;

    private String title;

    private Date date;

    @Builder
    private ResSimpleDiaryDto(Long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }
}
