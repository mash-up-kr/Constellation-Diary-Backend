package com.kancho.byeolbyeol.diary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "diaries")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usersId;

    private String date;

    private String title;

    private String content;

    @Builder
    private Diary(Long userId, String date, String title, String content) {
        this.usersId = userId;
        this.date = date;
        this.title = title;
        this.content = content;
    }
}
