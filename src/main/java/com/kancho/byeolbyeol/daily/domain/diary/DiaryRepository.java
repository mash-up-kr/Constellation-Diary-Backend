package com.kancho.byeolbyeol.daily.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByUsersIdAndDateBetween(Long userId, LocalDateTime startTime, LocalDateTime endTime);

    List<Diary> findByUsersIdAndDateGreaterThanEqualAndDateLessThan(Long userId, LocalDateTime startTime, LocalDateTime endTime);
}
