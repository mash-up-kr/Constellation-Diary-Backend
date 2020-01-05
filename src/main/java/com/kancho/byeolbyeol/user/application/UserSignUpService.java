package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.dto.ReqEmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final static String SUBJECT = "인증번호입니다.";

    private final EmailService emailService;

    public void generateAuthenticationNumber(ReqEmailDto reqEmailDto) {

        emailService.sendAuthenticationNumberMail(reqEmailDto.getEmail(), SUBJECT, "1234");
    }
}
