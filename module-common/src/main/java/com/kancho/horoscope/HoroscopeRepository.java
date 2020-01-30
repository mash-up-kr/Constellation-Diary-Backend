package com.kancho.horoscope;

import com.kancho.constellation.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface HoroscopeRepository extends JpaRepository<Horoscope, Long> {
    Optional<Horoscope> findByConstellationAndDate(Constellation constellation, LocalDate toLocalDate);
}
