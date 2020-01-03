package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.dto.ReqEmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final EmailService emailService;

    public void generateAuthenticationNumber(ReqEmailDto reqEmailDto) {

        emailService.sendAuthenticationNumberMail("korea8378@naver.com", "인증번호입니다.", "1234");
    }
}
