package com.kancho.byeolbyeol.diary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "diaries")
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private String title;

    private String content;

    private Long usersId;

    private Long horoscopeId;

    @Builder
    private Diary(Long userId, String date, String title, String content) {
        this.usersId = userId;
        this.date = date;
        this.title = title;
        this.content = content;
    }
}
