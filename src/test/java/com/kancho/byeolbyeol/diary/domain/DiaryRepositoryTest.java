package com.kancho.byeolbyeol.diary.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class DiaryRepositoryTest {

    @Autowired
    DiaryRepository diaryRepository;

    @After
    public void cleanup() {
        diaryRepository.deleteAll();
    }

    @Test
    public void 일기_저장하고_불러오기() {
        //given
        diaryRepository.save(Diary.builder()
                .userId(1L)
                .date(LocalDate.now())
                .title("제목")
                .content("내용")
                .build());

        //when
        List<Diary> diaryList = diaryRepository.findAll();

        //then
        Diary diary = diaryList.get(0);
        assertThat(diary.getUsersId(), is(1L));
        assertThat(diary.getTitle(), is("제목"));
        assertThat(diary.getContent(), is("내용"));
    }
}