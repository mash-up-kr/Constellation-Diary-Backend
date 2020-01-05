package com.kancho.byeolbyeol.authentication;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthenticationAspect {

    private final JWTManager jwtManager;

    @Pointcut("execution(public * com.kancho.byeolbyeol.user.controller.UserController.signUp(..))")
    public void userController() {
    }

    @Before(value = "userController()")
    public void checkRegisterJWT() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder
                        .currentRequestAttributes())
                        .getRequest();

        String token = request.getHeader("Authorization");

        if (isEmptyToken(token)) {
            throw new FailAuthenticationException();
        }
        jwtManager.authenticate(token);
    }

    private boolean isEmptyToken(String token) {
        return token == null;
    }
}
