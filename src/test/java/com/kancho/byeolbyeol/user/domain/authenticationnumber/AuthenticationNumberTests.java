package com.kancho.byeolbyeol.user.domain.authenticationnumber;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationNumberTests {

    @Test
    public void 인증번호가_같은지_검사() {
        Long sameNumber = 495832L;
        Long differentNumber = 123321L;

        AuthenticationNumber authenticationNumber = AuthenticationNumber.builder()
                .expirationTime(100000L)
                .number(sameNumber.toString())
                .email("test@naver.com")
                .build();

        assertThat(authenticationNumber.isNotEqualNumber(sameNumber)).isFalse();
        assertThat(authenticationNumber.isNotEqualNumber(differentNumber)).isTrue();

    }
}
