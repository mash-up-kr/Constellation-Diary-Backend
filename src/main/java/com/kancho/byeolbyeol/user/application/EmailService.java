package com.kancho.byeolbyeol.user.application;

public interface EmailService {
    void sendAuthenticationNumberMail(String to, String subject, String authenticationNumber);
}
