package com.kancho.byeolbyeol.diary.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
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
                .date("2019년 11월 23일")
                .title("제목")
                .content("내용")
                .build());

        //when
        List<Diary> diaryList = diaryRepository.findAll();

        //then
        Diary diary = diaryList.get(0);
        assertThat(diary.getUsersId(), is(1L));
        assertThat(diary.getDate(), is("2019년 11월 23일"));
        assertThat(diary.getTitle(), is("제목"));
        assertThat(diary.getContent(), is("내용"));
    }
}