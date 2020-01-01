package com.kancho.byeolbyeol.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;

    private String password;

    private Integer constellationsId;

    private Boolean horoscopeAlarmFlag;

    private String questionTime;

    private Boolean questionAlarmFlag;

    @Builder
    private User(String userId, String password, Integer constellationsId,
                 Boolean horoscopeAlarmFlag, String questionTime, Boolean questionAlarmFlag) {
        this.userId = userId;
        this.password = password;
        this.constellationsId = constellationsId;
        this.horoscopeAlarmFlag = horoscopeAlarmFlag;
        this.questionTime = questionTime;
        this.questionAlarmFlag = questionAlarmFlag;
    }
}
