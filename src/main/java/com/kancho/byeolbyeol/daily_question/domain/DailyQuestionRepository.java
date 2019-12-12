package com.kancho.byeolbyeol.daily_question.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyQuestionRepository extends JpaRepository<DailyQuestion, Long> {
}
