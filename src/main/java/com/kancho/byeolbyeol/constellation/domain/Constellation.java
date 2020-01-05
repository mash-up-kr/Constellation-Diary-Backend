package com.kancho.byeolbyeol.constellation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "constellations")
@Entity
public class Constellation {

    @Id
    private Long id;

    private String name;

    private String date;

    private String description;
}
