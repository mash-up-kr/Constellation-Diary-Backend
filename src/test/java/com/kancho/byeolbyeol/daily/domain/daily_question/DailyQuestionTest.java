package com.kancho.byeolbyeol.daily.domain.daily_question;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DailyQuestionTest {

    @Test
    public void 오늘의질문_생성() {
        DailyQuestion dailyQuestion = DailyQuestion.builder()
                .date(LocalDate.now())
                .content("오늘 가장 감사한 사람은 누구인가요?")
                .build();

        assertThat(dailyQuestion.getContent(), is("오늘 가장 감사한 사람은 누구인가요?"));
    }
}