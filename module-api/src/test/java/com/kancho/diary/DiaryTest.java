package com.kancho.diary;

import com.kancho.daily.domain.Diary;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DiaryTest {

    @Test
    public void 일기_생성() {
        Diary diary = Diary.builder()
                .userId(1L)
                .date(LocalDateTime.now())
                .title("제목")
                .content("내용")
                .build();

        assertThat(diary.getUsersId(), is(1L));
        assertThat(diary.getTitle(), is("제목"));
        assertThat(diary.getContent(), is("내용"));
    }
}
