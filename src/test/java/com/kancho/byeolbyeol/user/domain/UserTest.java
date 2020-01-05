package com.kancho.byeolbyeol.user.domain;

import com.kancho.byeolbyeol.user.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {

    @Test
    public void 유저_생성() {
        User user = User.builder()
                .userId("test")
                .password("test123")
                .constellationsId(1)
                .horoscopeAlarmFlag(true)
                .questionTime("09:00")
                .questionAlarmFlag(true)
                .build();

        assertThat(user.getUserId(), is("test"));
        assertThat(user.getPassword(), is("test123"));
        assertThat(user.getConstellationsId(), is(1));
        assertThat(user.getHoroscopeAlarmFlag(), is(true));
        assertThat(user.getQuestionTime(), is("09:00"));
        assertThat(user.getQuestionAlarmFlag(), is(true));
    }

}