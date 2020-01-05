package com.kancho.byeolbyeol.daily_question.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "daily_questions")
@Entity
public class DailyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private String content;

    @Builder
    private DailyQuestion(String date, String content) {
        this.date = date;
        this.content = content;
    }
}
