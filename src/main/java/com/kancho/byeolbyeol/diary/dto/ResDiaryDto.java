package com.kancho.byeolbyeol.diary.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResDiaryDto {

    private Long id;

    private Date date;

    private String title;

    private String content;

    private Long horoscopeId;

    @Builder
    private ResDiaryDto(Long id, Date date, String title, String content, Long horoscopeId) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.horoscopeId = horoscopeId;
    }
}
