package com.kancho.byeolbyeol.daily_question.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class DailyQuestionRepositoryTest {

    @Autowired
    DailyQuestionRepository dailyQuestionRepository;

    @After
    public void cleanup() {
        dailyQuestionRepository.deleteAll();
    }

    @Test
    public void 오늘의질문_저장하고_불러오기() {
        //given
        dailyQuestionRepository.save(DailyQuestion.builder()
                .date("2019년 12월 12일")
                .content("오늘 가장 감사한 사람은 누구인가요?")
                .build());

        //when
        List<DailyQuestion> dailyQuestionList = dailyQuestionRepository.findAll();

        //then
        DailyQuestion dailyQuestion = dailyQuestionList.get(0);
        assertThat(dailyQuestion.getDate(), is("2019년 12월 12일"));
        assertThat(dailyQuestion.getContent(), is("오늘 가장 감사한 사람은 누구인가요?"));
    }
}