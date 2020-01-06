package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumber;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumberRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqAuthenticationNumbersDto;
import com.kancho.byeolbyeol.util.RandomNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationEmailService {
    private final static String SUBJECT = "별별일기 인증번호입니다.";
    private final static String CONTENT = " 인증번호 : ";
    private final static Long THREE_MINUTES = 180000L;

    private final EmailService emailService;
    private final AuthenticationNumberRepository authenticationNumberRepository;

    public void sendAuthenticationNumber(ReqAuthenticationNumbersDto reqAuthenticationNumbersDto) {
        String number = createAuthenticationNumber();

        emailService.sendAuthenticationNumberMail(
                reqAuthenticationNumbersDto.getEmail(),
                SUBJECT, CONTENT + number);

        AuthenticationNumber authenticationNumber = AuthenticationNumber.builder()
                .email(reqAuthenticationNumbersDto.getEmail())
                .number(number)
                .expirationTime(System.currentTimeMillis() + THREE_MINUTES)
                .build();

        authenticationNumberRepository.save(authenticationNumber);
    }

    private String createAuthenticationNumber() {
        return RandomNumber.generateNumber().toString();
    }

}
