package com.kancho.byeolbyeol.diary.domain;

import com.kancho.byeolbyeol.common.entity_converter.LocalDateTimePersistenceConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "diaries")
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime date;

    private String title;

    @Lob
    private String content;

    private Long usersId;

    private Long horoscopeId;

    @Builder
    private Diary(Long userId, LocalDateTime date, String title, String content) {
        this.usersId = userId;
        this.date = date;
        this.title = title;
        this.content = content;
    }
}
