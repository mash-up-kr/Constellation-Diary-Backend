package com.kancho.byeolbyeol.user.application;

import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async
    void sendAuthenticationNumberMail(String to, String subject, String authenticationNumber);
}
