package com.kancho.byeolbyeol.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqWriteDiaryDto {

    private String title;

    private String content;
}
