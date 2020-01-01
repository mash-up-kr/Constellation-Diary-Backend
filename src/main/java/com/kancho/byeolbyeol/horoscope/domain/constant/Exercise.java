package com.kancho.byeolbyeol.horoscope.domain.constant;

public enum Exercise {
    GOLF("골프"),
    HIKING("등산"),
    BICYCLE("자전거"),
    RUNNING("달리기"),
    DANCE("춤"),
    MUSCULAR_EXERCISE("근력운동"),
    SWIMMING("수영"),
    TABLE_TENNIS("탁구"),
    HORSEBACK_RIDING("승마"),
    BADMINTON("배드민턴"),
    BARE_HANDED_EXERCISE("맨손운동"),
    JUMP_ROPE("줄넘기"),
    BOWLING("볼링"),
    YOGA("요가"),
    STRETCHING("스트레칭"),
    WALK("걷기"),
    INLINE("인라인");

    private String value;

    Exercise(String value) {
        this.value = value;
    }
}
