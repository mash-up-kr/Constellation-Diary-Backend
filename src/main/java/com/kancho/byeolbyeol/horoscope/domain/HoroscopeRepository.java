package com.kancho.byeolbyeol.horoscope.domain;

import com.kancho.byeolbyeol.common.constant.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface HoroscopeRepository extends JpaRepository<Horoscope, Long> {
    Optional<Horoscope> findByConstellationAndDate(Constellation constellation, LocalDate toLocalDate);
}
