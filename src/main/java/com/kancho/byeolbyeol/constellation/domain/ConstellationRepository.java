package com.kancho.byeolbyeol.constellation.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstellationRepository extends JpaRepository<Constellation, Integer> {
    Optional<Constellation> findByName(String constellation);
}
