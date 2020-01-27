package com.kancho.byeolbyeol.user.domain.user;

import com.kancho.byeolbyeol.common.entity_converter.LocalTimePersistenceConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;

    private String password;

    private String email;

    private Long constellationsId;

    @Convert(converter = LocalTimePersistenceConverter.class)
    @Column(name = "horoscope_time", columnDefinition = "TIME")
    private LocalTime horoscopeTime;

    private Boolean horoscopeAlarmFlag;

    @Convert(converter = LocalTimePersistenceConverter.class)
    @Column(name = "question_time", columnDefinition = "TIME")
    private LocalTime questionTime;

    private Boolean questionAlarmFlag;

    @Lob
    private String fcmToken;

    @Builder
    private User(String userId, String password, Long constellationsId, String email,
                 LocalTime questionTime, LocalTime horoscopeTime) {
        this.userId = userId;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.constellationsId = constellationsId;
        this.email = email;
        this.horoscopeAlarmFlag = true;
        this.questionAlarmFlag = true;
        this.questionTime = questionTime;
        this.horoscopeTime = horoscopeTime;
    }

    public boolean isPreviousQuestionTime(LocalTime localTime, Long deadline) {
        if (localTime.getHour() >= deadline) {
            return false;
        }

        int compare = this.questionTime.compareTo(localTime);
        return compare > 0;
    }

    public void modifyConstellation(Long constellationsId) {
        this.constellationsId = constellationsId;
    }

    public void modifyQuestionAlarm(Boolean questionAlarm) {
        this.questionAlarmFlag = questionAlarm;
    }

    public void modifyHoroscopeAlarm(Boolean horoscopeAlarm) {
        this.horoscopeAlarmFlag = horoscopeAlarm;
    }

    public void modifyQuestionTime(LocalTime questionTime) {
        this.questionTime = questionTime;
    }

    public void modifyHoroscopeTime(LocalTime horoscopeTime) {
        this.horoscopeTime = horoscopeTime;
    }

    public void modifyPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void saveFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public boolean isNotEqualToPassword(String password) {
        return !BCrypt.checkpw(password, this.password);
    }

    public void removeFcmToken() {
        this.fcmToken = null;
    }
}
