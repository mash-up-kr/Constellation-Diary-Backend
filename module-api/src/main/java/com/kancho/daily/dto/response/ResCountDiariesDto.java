package com.kancho.daily.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResCountDiariesDto {

    private String timeZone;
    private ResCountYearDiaryDto diaries;

    @Builder
    private ResCountDiariesDto(ResCountYearDiaryDto diaries, String timeZone) {
        this.diaries = diaries;
        this.timeZone = timeZone;
    }
}
