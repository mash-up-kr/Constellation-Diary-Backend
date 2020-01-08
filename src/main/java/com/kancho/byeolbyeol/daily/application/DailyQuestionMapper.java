package com.kancho.byeolbyeol.daily.application;

import com.kancho.byeolbyeol.daily.domain.diary.Diary;
import com.kancho.byeolbyeol.daily.dto.ResDailyQuestionDto;
import org.springframework.stereotype.Component;

@Component
public class DailyQuestionMapper {
    private final static Long NONE_DIARY_ID = -1L;
    private final static Boolean EXIST = true;
    private final static Boolean NOT_EXIST = false;


    public ResDailyQuestionDto toResHomeViewDto(Diary diary) {
        return ResDailyQuestionDto.builder()
                .existDiary(EXIST)
                .question(diary.getTitle())
                .diaryId(diary.getId())
                .build();
    }

    public ResDailyQuestionDto toResHomeViewDto(String question) {
        return ResDailyQuestionDto.builder()
                .existDiary(NOT_EXIST)
                .question(question)
                .diaryId(NONE_DIARY_ID)
                .build();
    }

}
