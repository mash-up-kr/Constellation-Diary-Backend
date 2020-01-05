package com.kancho.byeolbyeol.user.infrastructure;

import com.kancho.byeolbyeol.user.application.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JavaMailSenderEmailService implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendAuthenticationNumberMail(String to, String subject, String authenticationNumber) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(authenticationNumber);
        javaMailSender.send(message);
    }
}
