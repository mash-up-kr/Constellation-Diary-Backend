package com.kancho.byeolbyeol.daily.domain.daily_question;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
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
                .date(LocalDate.now())
                .content("오늘 가장 감사한 사람은 누구인가요?")
                .build());

        //when
        List<DailyQuestion> dailyQuestionList = dailyQuestionRepository.findAll();

        //then
        DailyQuestion dailyQuestion = dailyQuestionList.get(0);
        assertThat(dailyQuestion.getContent(), is("오늘 가장 감사한 사람은 누구인가요?"));
    }
}