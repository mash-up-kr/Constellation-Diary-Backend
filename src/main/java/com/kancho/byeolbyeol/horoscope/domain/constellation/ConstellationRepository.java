package com.kancho.byeolbyeol.horoscope.domain.constellation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
    Optional<Constellation> findByName(String constellation);
}
