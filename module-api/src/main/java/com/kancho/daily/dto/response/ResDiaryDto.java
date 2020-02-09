package com.kancho.daily.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResDiaryDto {

    private Long id;

    private String timeZone;

    private Date date;

    private String title;

    private String content;

    private Long horoscopeId;

    @Builder
    private ResDiaryDto(Long id, String timeZone, Date date, String title, String content, Long horoscopeId) {
        this.id = id;
        this.timeZone = timeZone;
        this.date = date;
        this.title = title;
        this.content = content;
        this.horoscopeId = horoscopeId;
    }
}
