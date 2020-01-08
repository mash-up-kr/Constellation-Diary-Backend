package com.kancho.byeolbyeol.home.application;

import com.kancho.byeolbyeol.diary.domain.Diary;
import com.kancho.byeolbyeol.home.dto.ResHomeViewDto;
import org.springframework.stereotype.Component;

@Component
public class HomeMapper {
    private final static Long NONE_DIARY_ID = -1L;
    private final static Boolean EXIST = true;
    private final static Boolean NOT_EXIST = false;


    public ResHomeViewDto toResHomeViewDto(Diary diary) {
        return ResHomeViewDto.builder()
                .existDiary(EXIST)
                .question(diary.getTitle())
                .diaryId(diary.getId())
                .build();
    }

    public ResHomeViewDto toResHomeViewDto(String question) {
        return ResHomeViewDto.builder()
                .existDiary(NOT_EXIST)
                .question(question)
                .diaryId(NONE_DIARY_ID)
                .build();
    }

}
