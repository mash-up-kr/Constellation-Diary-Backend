package com.kancho.byeolbyeol.horoscope.application;

import com.kancho.byeolbyeol.common.constant.Constellation;
import com.kancho.byeolbyeol.common.constant.ReqTimeZone;
import com.kancho.byeolbyeol.common.util.TimeCalculate;
import com.kancho.byeolbyeol.horoscope.exception.NotFoundHoroscopeException;
import com.kancho.byeolbyeol.horoscope.domain.Horoscope;
import com.kancho.byeolbyeol.horoscope.domain.HoroscopeRepository;
import com.kancho.byeolbyeol.horoscope.dto.ResHoroscopeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class HoroscopeService {
    private final HoroscopeRepository horoscopeRepository;

    public ResHoroscopeDto findHoroscope(String constellationName, Date date, ReqTimeZone reqTimeZone) {
        LocalDateTime nowDateTime = TimeCalculate.covertLocalDateTime(date);
        LocalDate nowLocalDate = TimeCalculate.covertLocalDate(nowDateTime, reqTimeZone);

        Constellation constellation = Constellation.findByConstellation(constellationName);

        Horoscope horoscope = horoscopeRepository
                .findByConstellationAndDate(constellation, nowLocalDate)
                .orElseThrow(NotFoundHoroscopeException::new);

        return toResHoroscopeDto(horoscope);
    }

    public ResHoroscopeDto findHoroscope(Long horoscopeId) {
        Horoscope horoscope = horoscopeRepository
                .findById(horoscopeId)
                .orElseThrow(NotFoundHoroscopeException::new);

        return toResHoroscopeDto(horoscope);
    }

    private ResHoroscopeDto toResHoroscopeDto(Horoscope horoscope) {
        return ResHoroscopeDto.builder()
                .id(horoscope.getId())
                .constellation(horoscope.getConstellation().getValue())
                .content(horoscope.getContent())
                .date(horoscope.getDate())
                .exercise(horoscope.getExercise().getValue())
                .numeral(horoscope.getNumeral().getValue())
                .stylist(horoscope.getStylist().getValue())
                .word(horoscope.getWord().getValue())
                .build();
    }
}
