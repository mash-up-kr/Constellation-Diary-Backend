package com.kancho.byeolbyeol.diary.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DiaryTest {

    @Test
    public void 일기_생성() {
        Diary diary = Diary.builder()
                .userId(1L)
                .date("2019년 11월 23일")
                .title("제목")
                .content("내용")
                .build();

        assertThat(diary.getUsersId(), is(1L));
        assertThat(diary.getDate(), is("2019년 11월 23일"));
        assertThat(diary.getTitle(), is("제목"));
        assertThat(diary.getContent(), is("내용"));
    }
}
