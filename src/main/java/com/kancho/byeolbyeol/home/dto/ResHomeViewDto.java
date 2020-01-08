package com.kancho.byeolbyeol.home.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResHomeViewDto {

    private Boolean existDiary;

    private String question;

    private Long diaryId;

    @Builder
    private ResHomeViewDto (Boolean existDiary, String question,
                            Long diaryId) {
        this.existDiary = existDiary;
        this.question = question;
        this.diaryId = diaryId;
    }
}
