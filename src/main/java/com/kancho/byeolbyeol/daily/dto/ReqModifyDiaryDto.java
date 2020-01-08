package com.kancho.byeolbyeol.daily.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqModifyDiaryDto {

    @NotNull
    private String title;

    @NotNull
    private String content;
}
