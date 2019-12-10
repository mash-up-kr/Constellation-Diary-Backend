package com.kancho.byeolbyeol.user.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {

    @Test
    public void 유저_생성() {
        User user = User.builder()
                .constellationsId(1)
                .deviceId("test")
                .horoscopeAlarmFlag(true)
                .questionTime("09:00")
                .questionAlarmFlag(true)
                .build();

        assertThat(user.getConstellationsId(), is(1));
        assertThat(user.getDeviceId(), is("test"));
        assertThat(user.getHoroscopeAlarmFlag(), is(true));
        assertThat(user.getQuestionTime(), is("09:00"));
        assertThat(user.getQuestionAlarmFlag(), is(true));
    }

}