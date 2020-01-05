package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumber;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumberRepository;
import com.kancho.byeolbyeol.user.dto.ReqEmailDto;
import com.kancho.byeolbyeol.util.RandomNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserAuthenticationEmailService {
    private final static String SUBJECT = "별별일기 이메일 인증번호입니다.";
    private final static String CONTENT = "인증번호 : ";
    private final static Long THREE_MINUTES = 180000L;

    private final EmailService emailService;
    private final AuthenticationNumberRepository authenticationNumberRepository;

    public void generateAuthenticationNumber(ReqEmailDto reqEmailDto) {
        String number = RandomNumber.generateNumber().toString();

        emailService.sendAuthenticationNumberMail(reqEmailDto.getEmail(),
                SUBJECT, CONTENT + number);

        AuthenticationNumber authenticationNumber = AuthenticationNumber.builder()
                .email(reqEmailDto.getEmail())
                .number(number)
                .expirationTime(System.currentTimeMillis() + THREE_MINUTES)
                .build();

        authenticationNumberRepository.save(authenticationNumber);
    }

}
