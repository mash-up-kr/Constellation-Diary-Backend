package com.kancho.user.application;

import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async("mailSenderThreadPool")
    void sendAuthenticationNumberMail(String to, String subject, String authenticationNumber);
}
