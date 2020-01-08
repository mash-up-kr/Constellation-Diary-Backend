package com.kancho.byeolbyeol.authentication;

import com.kancho.byeolbyeol.common.exception.FailAuthenticationException;
import com.kancho.byeolbyeol.common.user_context.ThreadContext;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
@RequiredArgsConstructor
public class AuthenticationAspect {

    private final JWTManager jwtManager;

    @Pointcut("execution(public * com.kancho.byeolbyeol.user.controller.UserController.signUp(..))")
    public void signUp() {
    }

    @Pointcut("execution(public * com.kancho.byeolbyeol.user.controller.UserController.refreshToken(..))")
    public void refreshToken() {
    }

    @Pointcut("execution(public * com.kancho.byeolbyeol.home.controller.HomeController.*(..))")
    public void homeController() {
    }

    @Pointcut("execution(public * com.kancho.byeolbyeol.diary.controller.DiaryController.*(..))")
    public void diaryController() {
    }

    @Before(value = "signUp()")
    public void checkRegisterToken() {
        String token = getToken();

        if (isEmptyToken(token)) {
            throw new FailAuthenticationException();
        }
        jwtManager.authenticate(token);
    }

    @Before(value = "refreshToken()")
    public void checkRefreshToken() {
        String token = getToken();

        if (isEmptyToken(token)) {
            throw new FailAuthenticationException();
        }
        UserInfo userInfo = jwtManager.getUserInfo(token, TokenType.REFRESH_TOKEN::verifyValue);

        ThreadContext.userInfo.set(userInfo);
    }

    @Before(value = "homeController() || diaryController()")
    public void checkAuthenticationToken() {
        String token = getToken();

        if (isEmptyToken(token)) {
            throw new FailAuthenticationException();
        }
        UserInfo userInfo = jwtManager.getUserInfo(token, TokenType.AUTHENTICATION_TOKEN::verifyValue);

        ThreadContext.userInfo.set(userInfo);
    }

    private String getToken() {
        return ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest().getHeader("Authorization");
    }

    private boolean isEmptyToken(String token) {
        return token == null;
    }
}
