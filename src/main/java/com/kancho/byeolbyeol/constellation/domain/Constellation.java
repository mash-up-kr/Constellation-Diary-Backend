package com.kancho.byeolbyeol.constellation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "constellations")
public class Constellation {

    @Id
    private Integer id;

    private String name;

    private String date;

    private String description;

    private String imageUrl;

    private String iconUrl;
}
