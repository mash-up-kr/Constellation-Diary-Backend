package com.kancho.byeolbyeol.user.domain.sign_up_numbers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpNumberTests {

    @Test
    public void 인증번호가_같은지_검사() {
        Long sameNumber = 495832L;
        Long differentNumber = 123321L;

        SignUpNumber signUpNumber = SignUpNumber.builder()
                .expirationTime(100000L)
                .number(sameNumber.toString())
                .email("test@naver.com")
                .build();

        assertThat(signUpNumber.isNotEqualNumber(sameNumber)).isFalse();
        assertThat(signUpNumber.isNotEqualNumber(differentNumber)).isTrue();

    }
}
