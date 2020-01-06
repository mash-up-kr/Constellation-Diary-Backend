package com.kancho.byeolbyeol.horoscope.domain;

import com.kancho.byeolbyeol.horoscope.domain.constant.Exercise;
import com.kancho.byeolbyeol.horoscope.domain.constant.Numeral;
import com.kancho.byeolbyeol.horoscope.domain.constant.Stylist;
import com.kancho.byeolbyeol.horoscope.domain.constant.Word;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "horoscopes")
@Entity
public class Horoscope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private Stylist stylist;

    @Enumerated(EnumType.STRING)
    @Column(name = "number")
    private Numeral numeral;

    @Enumerated(EnumType.STRING)
    private Word word;

    @Enumerated(EnumType.STRING)
    private Exercise exercise;

    private Long constellationsId;

    @Builder
    private Horoscope(Long constellationsId, String date, String content,
                      Stylist stylist, Numeral numeral, Word word, Exercise exercise) {
        this.constellationsId = constellationsId;
        this.date = date;
        this.content = content;
        this.stylist = stylist;
        this.numeral = numeral;
        this.word = word;
        this.exercise = exercise;
    }
}
