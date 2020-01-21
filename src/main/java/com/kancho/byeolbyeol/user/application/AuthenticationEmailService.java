package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.common.util.RandomNumber;
import com.kancho.byeolbyeol.user.domain.find_password_number.FindPasswordNumber;
import com.kancho.byeolbyeol.user.domain.find_password_number.FindPasswordNumberRepository;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumber;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumberRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqFindPasswordNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpNumberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationEmailService {
    private final static Long THREE_MINUTES = 180000L;

    private final EmailService emailService;
    private final SignUpNumberRepository signUpNumberRepository;
    private final FindPasswordNumberRepository findPasswordNumberRepository;

    public void sendSignUpNumber(ReqSignUpNumberDto reqSignUpNumberDto) {
        String number = createAuthenticationNumber();

        emailService.sendAuthenticationNumberMail(
                reqSignUpNumberDto.getEmail(),
                MailForm.SIGN_UP.getSubject(), MailForm.SIGN_UP.getContent() + number);

        SignUpNumber signUpNumber = SignUpNumber.builder()
                .email(reqSignUpNumberDto.getEmail())
                .number(number)
                .expirationTime(System.currentTimeMillis() + THREE_MINUTES)
                .build();

        signUpNumberRepository.save(signUpNumber);
    }

    public void sendFindPasswordNumber(ReqFindPasswordNumberDto reqFindPasswordNumberDto) {
        String number = createAuthenticationNumber();

        emailService.sendAuthenticationNumberMail(
                reqFindPasswordNumberDto.getEmail(),
                MailForm.FIND_PASSWORD.getSubject(), MailForm.FIND_PASSWORD.getContent() + number);

        FindPasswordNumber findPasswordNumber = FindPasswordNumber.builder()
                .email(reqFindPasswordNumberDto.getEmail())
                .userId(reqFindPasswordNumberDto.getUserId())
                .number(number)
                .expirationTime(System.currentTimeMillis() + THREE_MINUTES)
                .build();

        findPasswordNumberRepository.save(findPasswordNumber);
    }


    private String createAuthenticationNumber() {
        return RandomNumber.generateNumber().toString();
    }

}
