package com.kancho.horoscope;

import com.kancho.constellation.Constellation;
import com.kancho.entity_converter.LocalDatePersistenceConverter;
import com.kancho.horoscope.constant.Exercise;
import com.kancho.horoscope.constant.Numeral;
import com.kancho.horoscope.constant.Stylist;
import com.kancho.horoscope.constant.Word;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "horoscopes")
@Entity
public class Horoscope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate date;

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

    @Enumerated(EnumType.STRING)
    private Constellation constellation;

    @Builder
    private Horoscope(Constellation constellation, LocalDate date, String content,
                      Stylist stylist, Numeral numeral, Word word, Exercise exercise) {
        this.constellation = constellation;
        this.date = date;
        this.content = content;
        this.stylist = stylist;
        this.numeral = numeral;
        this.word = word;
        this.exercise = exercise;
    }
}
