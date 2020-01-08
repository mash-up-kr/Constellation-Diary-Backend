package com.kancho.byeolbyeol.horoscope.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HoroscopeRepository extends JpaRepository<Horoscope, Long> {
    Optional<Horoscope> findByConstellationsIdAndDate(Long id, LocalDate toLocalDate);
}
