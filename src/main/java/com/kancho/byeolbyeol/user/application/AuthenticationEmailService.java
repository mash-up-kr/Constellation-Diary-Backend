package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumber;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumberRepository;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationPurpose;
import com.kancho.byeolbyeol.user.dto.requset.ReqAuthenticationNumbersDto;
import com.kancho.byeolbyeol.util.RandomNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationEmailService {
    private final static String SUBJECT_FIRST = "별별일기 ";
    private final static String SUBJECT_END = " 인증번호입니다.";
    private final static String CONTENT = " 인증번호 : ";
    private final static Long THREE_MINUTES = 180000L;

    private final EmailService emailService;
    private final AuthenticationNumberRepository authenticationNumberRepository;

    public void sendAuthenticationNumber(ReqAuthenticationNumbersDto reqAuthenticationNumbersDto) {
        String number = createAuthenticationNumber();

        AuthenticationPurpose purpose =
                toAuthenticationPurpose(reqAuthenticationNumbersDto.getAuthenticationPurpose());

        emailService.sendAuthenticationNumberMail(
                reqAuthenticationNumbersDto.getEmail(),
                SUBJECT_FIRST + purpose.getValue() + SUBJECT_END,
                purpose.getValue() + CONTENT + number);

        AuthenticationNumber authenticationNumber = AuthenticationNumber.builder()
                .email(reqAuthenticationNumbersDto.getEmail())
                .number(number)
                .expirationTime(System.currentTimeMillis() + THREE_MINUTES)
                .authenticationPurpose(purpose)
                .build();

        authenticationNumberRepository.save(authenticationNumber);
    }

    private String createAuthenticationNumber() {
        return RandomNumber.generateNumber().toString();
    }

    private AuthenticationPurpose toAuthenticationPurpose(String reqPurPose) {
        return AuthenticationPurpose.findByAuthenticationPurpose(reqPurPose);
    }

}
