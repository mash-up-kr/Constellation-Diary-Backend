package com.kancho.daily.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResDiariesDto {

    private List<ResSimpleDiaryDto> diaries;
    private String timeZone;

    @Builder
    private ResDiariesDto(List<ResSimpleDiaryDto> diaries, String timeZone) {
        this.diaries = diaries;
        this.timeZone = timeZone;
    }
}
