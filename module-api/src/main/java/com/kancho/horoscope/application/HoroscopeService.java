package com.kancho.horoscope.application;


import com.kancho.common.constant.ReqTimeZone;
import com.kancho.common.util.TimeCalculate;
import com.kancho.constellation.Constellation;
import com.kancho.horoscope.Horoscope;
import com.kancho.horoscope.HoroscopeRepository;
import com.kancho.horoscope.dto.ResHoroscopeDto;
import com.kancho.horoscope.exception.NotFoundHoroscopeException;
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
        System.out.println(nowDateTime);
        System.out.println(constellation.getValue());
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
