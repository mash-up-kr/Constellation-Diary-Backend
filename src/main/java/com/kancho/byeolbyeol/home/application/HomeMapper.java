package com.kancho.byeolbyeol.home.application;

import com.kancho.byeolbyeol.common.util.TimeConverter;
import com.kancho.byeolbyeol.constellation.domain.Constellation;
import com.kancho.byeolbyeol.diary.domain.Diary;
import com.kancho.byeolbyeol.home.dto.ResHomeViewDto;
import com.kancho.byeolbyeol.home.dto.ResHoroscopeDto;
import com.kancho.byeolbyeol.horoscope.domain.Horoscope;
import org.springframework.stereotype.Component;

@Component
public class HomeMapper {

    public ResHomeViewDto toResHomeViewDto(Diary diary, Horoscope horoscope, Constellation constellation) {
        return ResHomeViewDto.builder()
                .existDiary(true)
                .question(diary.getTitle())
                .diaryId(diary.getId())
                .resHoroscopeDto(toResHoroscopeDto(horoscope, constellation))
                .build();
    }

    public ResHomeViewDto toResHomeViewDto(String question, Horoscope horoscope, Constellation constellation) {
        return ResHomeViewDto.builder()
                .existDiary(false)
                .question(question)
                .diaryId(-1L)
                .resHoroscopeDto(toResHoroscopeDto(horoscope, constellation))
                .build();
    }

    private ResHoroscopeDto toResHoroscopeDto(Horoscope horoscope, Constellation constellation) {
        return ResHoroscopeDto.builder()
                .constellation(constellation.getName())
                .id(horoscope.getId())
                .content(horoscope.getContent())
                .date(TimeConverter.covertKstToUctDate(horoscope.getDate()))
                .exercise(horoscope.getExercise().getValue())
                .numeral(horoscope.getNumeral().getValue())
                .stylist(horoscope.getStylist().getValue())
                .word(horoscope.getWord().getValue())
                .build();
    }
}
