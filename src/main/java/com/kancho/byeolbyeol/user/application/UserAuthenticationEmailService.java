package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.dto.ReqEmailDto;
import com.kancho.byeolbyeol.util.RandomNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserAuthenticationEmailService {
    private final static String SUBJECT = "별별일기 이메일 인증번호입니다.";

    private final EmailService emailService;

    public void generateAuthenticationNumber(ReqEmailDto reqEmailDto) {

        emailService.sendAuthenticationNumberMail(reqEmailDto.getEmail(),
                SUBJECT, RandomNumber.generateNumber().toString());

    }
}
