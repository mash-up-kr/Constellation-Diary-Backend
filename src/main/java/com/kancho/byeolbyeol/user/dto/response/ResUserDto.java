package com.kancho.byeolbyeol.user.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class ResUserDto {

    private Long id;
    private String userId;
    private String constellation;
    private String timeZone;
    private Boolean horoscopeAlarmFlag;
    private Boolean questionAlarmFlag;
    private LocalTime horoscopeTime;
    private LocalTime questionTime;

    @Builder
    private ResUserDto(Long id, String userId, String constellation, String timeZone,
                       Boolean horoscopeAlarmFlag, Boolean questionAlarmFlag,
                       LocalTime horoscopeTime, LocalTime questionTime) {
        this.id = id;
        this.userId = userId;
        this.constellation = constellation;
        this.timeZone = timeZone;
        this.horoscopeAlarmFlag = horoscopeAlarmFlag;
        this.questionAlarmFlag = questionAlarmFlag;
        this.horoscopeTime = horoscopeTime;
        this.questionTime = questionTime;
    }
}
