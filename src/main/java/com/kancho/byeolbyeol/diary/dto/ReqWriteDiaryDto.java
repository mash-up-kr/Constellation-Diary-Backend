package com.kancho.byeolbyeol.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqWriteDiaryDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long horoscopeId;
}
