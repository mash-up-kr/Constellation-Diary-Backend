package com.kancho.byeolbyeol.daily.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResDailyQuestionDto {

    private Boolean existDiary;

    private String question;

    private Long diaryId;

    @Builder
    private ResDailyQuestionDto(Boolean existDiary, String question,
                                Long diaryId) {
        this.existDiary = existDiary;
        this.question = question;
        this.diaryId = diaryId;
    }
}
