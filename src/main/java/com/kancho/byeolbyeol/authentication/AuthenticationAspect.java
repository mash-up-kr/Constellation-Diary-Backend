package com.kancho.byeolbyeol.authentication;

import com.kancho.byeolbyeol.common.exception.FailAuthenticationException;
import com.kancho.byeolbyeol.common.user_context.ThreadContext;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
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

    @Pointcut("execution(public * com.kancho.byeolbyeol.user.controller.UserChangeController.*(..)) && " +
            "!execution(public * com.kancho.byeolbyeol.user.controller.UserChangeController.modifyPassword(..))")
    public void userChangeController() {
    }

    @Pointcut("execution(public * com.kancho.byeolbyeol.user.controller.UserController.refreshToken(..))")
    public void refreshToken() {
    }

    @Pointcut("execution(public * com.kancho.byeolbyeol.user.controller.UserController.signOut(..))")
    public void userSignOut() {
    }

    @Pointcut("execution(public * com.kancho.byeolbyeol.daily.controller.DailyQuestionController.*(..))")
    public void dailyQuestionController() {
    }

    @Pointcut("execution(public * com.kancho.byeolbyeol.daily.controller.DiaryController.*(..))")
    public void diaryController() {
    }

    @Before(value = "signUp()")
    public void checkRegisterToken() {
        String token = getToken();

        if (isEmptyToken(token)) {
            throw new FailAuthenticationException();
        }
        jwtManager.authenticate(token, TokenType.SIGN_UP_TOKEN::verifyValue);
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

    @Before(value = "dailyQuestionController() || diaryController() || userChangeController() || userSignOut()")
    public void checkAuthenticationToken() {
        String token = getToken();

        if (isEmptyToken(token)) {
            throw new FailAuthenticationException();
        }
        UserInfo userInfo = jwtManager.getUserInfo(token, TokenType.AUTHENTICATION_TOKEN::verifyValue);

        ThreadContext.userInfo.set(userInfo);
    }

    @After(value = "dailyQuestionController() || diaryController() || userChangeController() || userSignOut()")
    public void removeThreadLocal() {
        ThreadContext.userInfo.remove();
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
