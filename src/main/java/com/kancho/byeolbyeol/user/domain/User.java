package com.kancho.byeolbyeol.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer constellationsId;

    private String deviceId;

    private Boolean horoscopeAlarmFlag;

    private String questionTime;

    private Boolean questionAlarmFlag;

    @Builder
    public User(Integer constellationsId, String deviceId, Boolean horoscopeAlarmFlag, String questionTime, Boolean questionAlarmFlag) {
        this.constellationsId = constellationsId;
        this.deviceId = deviceId;
        this.horoscopeAlarmFlag = horoscopeAlarmFlag;
        this.questionTime = questionTime;
        this.questionAlarmFlag = questionAlarmFlag;
    }
}
