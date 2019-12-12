package com.kancho.byeolbyeol.horoscope.domain;

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
@Entity(name = "horoscopes")
public class Horoscope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer constellationsId;

    private String date;

    private String content;

    private String stylist;

    private Integer number;

    private String word;

    private String exercise;




    @Builder
    public Horoscope(Integer constellationsId, String date, String content, String stylist, Integer number, String word, String exercise) {
        this.constellationsId = constellationsId;
        this.date = date;
        this.content = content;
        this.stylist = stylist;
        this.number = number;
        this.word = word;
        this.exercise = exercise;
    }
}
