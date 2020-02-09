package com.kancho.daily.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResCountDiariesDto {

    private String timeZone;
    private List<ResCountYearDiaryDto> diaries;

    @Builder
    private ResCountDiariesDto(List<ResCountYearDiaryDto> diaries, String timeZone) {
        this.diaries = diaries;
        this.timeZone = timeZone;
    }
}
