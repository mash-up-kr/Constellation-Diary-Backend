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

    private Boolean horoscopeAlarmFlag;

    @Convert(converter = LocalTimePersistenceConverter.class)
    @Column(name = "question_time", columnDefinition = "TIME")
    private LocalTime questionTime;

    private Boolean questionAlarmFlag;

    @Builder
    private User(String userId, String password, Long constellationsId, String email) {
        this.userId = userId;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.constellationsId = constellationsId;
        this.email = email;
        this.horoscopeAlarmFlag = true;
        this.questionAlarmFlag = true;
        this.questionTime = LocalTime.of(13, 0);
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
}
