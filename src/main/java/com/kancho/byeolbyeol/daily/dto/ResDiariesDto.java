package com.kancho.byeolbyeol.daily.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResDiariesDto {

    private List<ResSimpleDiaryDto> diaries;

    @Builder
    private ResDiariesDto(List<ResSimpleDiaryDto> diaries) {
        this.diaries = diaries;
    }
}
