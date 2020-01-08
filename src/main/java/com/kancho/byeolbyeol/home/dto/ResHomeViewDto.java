package com.kancho.byeolbyeol.home.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResHomeViewDto {

    private Boolean existDiary;

    private String question;

    private Long diaryId;

    private ResHoroscopeDto horoscope;

    @Builder
    private ResHomeViewDto (Boolean existDiary, String question,
                            Long diaryId, ResHoroscopeDto resHoroscopeDto) {
        this.existDiary = existDiary;
        this.question = question;
        this.diaryId = diaryId;
        this.horoscope = resHoroscopeDto;
    }
}
