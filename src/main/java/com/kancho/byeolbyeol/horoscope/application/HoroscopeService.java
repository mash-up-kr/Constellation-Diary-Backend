package com.kancho.byeolbyeol.horoscope.application;

import com.kancho.byeolbyeol.common.exception.NotFoundConstellationException;
import com.kancho.byeolbyeol.common.util.TimeCalculate;
import com.kancho.byeolbyeol.horoscope.exception.NotFoundHoroscopeException;
import com.kancho.byeolbyeol.horoscope.domain.constellation.Constellation;
import com.kancho.byeolbyeol.horoscope.domain.constellation.ConstellationRepository;
import com.kancho.byeolbyeol.horoscope.domain.horoscope.Horoscope;
import com.kancho.byeolbyeol.horoscope.domain.horoscope.HoroscopeRepository;
import com.kancho.byeolbyeol.horoscope.dto.ResHoroscopeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HoroscopeService {
    private final HoroscopeRepository horoscopeRepository;
    private final ConstellationRepository constellationRepository;

    public ResHoroscopeDto findHoroscope(String constellationName) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDate nowLocalDate = TimeCalculate.covertKstLocalDate(nowDateTime);

        Constellation constellation = constellationRepository.findByName(constellationName)
                .orElseThrow(NotFoundConstellationException::new);

        Horoscope horoscope = horoscopeRepository
                .findByConstellationsIdAndDate(constellation.getId(), nowLocalDate)
                .orElseThrow(NotFoundHoroscopeException::new);

        return toResHoroscopeDto(horoscope, constellation);
    }

    public ResHoroscopeDto findHoroscope(Long horoscopeId) {
        Horoscope horoscope = horoscopeRepository
                .findById(horoscopeId)
                .orElseThrow(NotFoundHoroscopeException::new);

        Constellation constellation = constellationRepository.findById(horoscope.getConstellationsId())
                .orElseThrow(NotFoundConstellationException::new);

        return toResHoroscopeDto(horoscope, constellation);
    }

    private ResHoroscopeDto toResHoroscopeDto(Horoscope horoscope, Constellation constellation) {
        return ResHoroscopeDto.builder()
                .id(horoscope.getId())
                .constellation(constellation.getName())
                .content(horoscope.getContent())
                .date(horoscope.getDate())
                .exercise(horoscope.getExercise().getValue())
                .numeral(horoscope.getNumeral().getValue())
                .stylist(horoscope.getStylist().getValue())
                .word(horoscope.getWord().getValue())
                .build();
    }
}
