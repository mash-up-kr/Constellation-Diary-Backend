package com.kancho.byeolbyeol.common;

import com.kancho.byeolbyeol.authentication.JWTManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class JWTManagerTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void 회원가입_인증용_JWT_생성() {
        JWTManager jwtManager = new JWTManager("안녕하세요 test용 키입니다.");
        String token = jwtManager.createSignUpToken("test@naver.com");

        assertThat(token).isNotEmpty();
    }

    @Test
    public void 유저_인증용_JWT_생성() {
        JWTManager jwtManager = new JWTManager("안녕하세요 test용 키입니다.");
        String token = jwtManager.createAuthenticationToken("testId", 1L);

        assertThat(token).isNotEmpty();
    }

    @Test
    public void 재발급용_JWT_생성() {
        JWTManager jwtManager = new JWTManager("안녕하세요 test용 키입니다.");
        String token = jwtManager.createRefreshToken("testId", 1L);

        assertThat(token).isNotEmpty();
    }
}
