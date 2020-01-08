package com.kancho.byeolbyeol.user.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class ResUserDto {

    private Long id;
    private String userId;
    private String constellation;
    private Boolean horoscopeAlarmFlag;
    private Boolean questionAlarmFlag;
    private String questionTime;

    @Builder
    private ResUserDto(Long id, String userId, String constellation,
                       Boolean horoscopeAlarmFlag, Boolean questionAlarmFlag, LocalTime questionTime) {
        this.id = id;
        this.userId = userId;
        this.constellation = constellation;
        this.horoscopeAlarmFlag = horoscopeAlarmFlag;
        this.questionAlarmFlag = questionAlarmFlag;
        this.questionTime = questionTime.toString();
    }
}
