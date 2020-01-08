package com.kancho.byeolbyeol.daily.domain;

import com.kancho.byeolbyeol.daily.domain.diary.Diary;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DiaryTest {

    @Test
    public void 일기_생성() {
        Diary diary = Diary.builder()
                .userId(1L)
                .date(LocalDate.now())
                .title("제목")
                .content("내용")
                .build();

        assertThat(diary.getUsersId(), is(1L));
        assertThat(diary.getTitle(), is("제목"));
        assertThat(diary.getContent(), is("내용"));
    }
}
