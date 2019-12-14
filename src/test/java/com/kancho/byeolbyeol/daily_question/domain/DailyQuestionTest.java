package com.kancho.byeolbyeol.daily_question.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DailyQuestionTest {

    @Test
    public void 오늘의질문_생성() {
        DailyQuestion dailyQuestion = DailyQuestion.builder()
                .date("2019년 12월 12일")
                .content("오늘 가장 감사한 사람은 누구인가요?")
                .build();

        assertThat(dailyQuestion.getDate(), is("2019년 12월 12일"));
        assertThat(dailyQuestion.getContent(), is("오늘 가장 감사한 사람은 누구인가요?"));
    }
}