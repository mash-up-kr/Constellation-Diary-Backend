package com.kancho.byeolbyeol.home.application;

import com.kancho.byeolbyeol.common.util.TimeCalculate;
import com.kancho.byeolbyeol.horoscope.domain.constellation.Constellation;
import com.kancho.byeolbyeol.diary.domain.Diary;
import com.kancho.byeolbyeol.home.dto.ResHomeViewDto;
import com.kancho.byeolbyeol.home.dto.ResHoroscopeDto;
import com.kancho.byeolbyeol.horoscope.domain.horoscope.Horoscope;
import org.springframework.stereotype.Component;

@Component
public class HomeMapper {
    private final static Long NONE_DIARY_ID = -1L;
    private final static Boolean EXIST = true;
    private final static Boolean NOT_EXIST = false;


    public ResHomeViewDto toResHomeViewDto(Diary diary, Horoscope horoscope, Constellation constellation) {
        return ResHomeViewDto.builder()
                .existDiary(EXIST)
                .question(diary.getTitle())
                .diaryId(diary.getId())
                .resHoroscopeDto(toResHoroscopeDto(horoscope, constellation))
                .build();
    }

    public ResHomeViewDto toResHomeViewDto(String question, Horoscope horoscope, Constellation constellation) {
        return ResHomeViewDto.builder()
                .existDiary(NOT_EXIST)
                .question(question)
                .diaryId(NONE_DIARY_ID)
                .resHoroscopeDto(toResHoroscopeDto(horoscope, constellation))
                .build();
    }

    private ResHoroscopeDto toResHoroscopeDto(Horoscope horoscope, Constellation constellation) {
        return ResHoroscopeDto.builder()
                .constellation(constellation.getName())
                .id(horoscope.getId())
                .content(horoscope.getContent())
                .date(TimeCalculate.covertKstToUctDate(horoscope.getDate()))
                .exercise(horoscope.getExercise().getValue())
                .numeral(horoscope.getNumeral().getValue())
                .stylist(horoscope.getStylist().getValue())
                .word(horoscope.getWord().getValue())
                .build();
    }
}
