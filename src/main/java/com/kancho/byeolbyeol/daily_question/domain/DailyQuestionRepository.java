package com.kancho.byeolbyeol.daily_question.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface DailyQuestionRepository extends JpaRepository<DailyQuestion, Long> {
    Optional<DailyQuestion> findByDate(LocalDate nowLocalDate);
}
