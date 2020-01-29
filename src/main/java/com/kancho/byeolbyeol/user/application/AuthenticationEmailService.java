package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.common.util.RandomNumber;
import com.kancho.byeolbyeol.user.domain.find_password_number.FindPasswordNumber;
import com.kancho.byeolbyeol.user.domain.find_password_number.FindPasswordNumberRepository;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumber;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumberRepository;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqFindPasswordNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpNumberDto;
import com.kancho.byeolbyeol.user.exception.ExistsEmailException;
import com.kancho.byeolbyeol.user.exception.IsNotExistsUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationEmailService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final SignUpNumberRepository signUpNumberRepository;
    private final FindPasswordNumberRepository findPasswordNumberRepository;

    public void sendSignUpNumber(ReqSignUpNumberDto reqSignUpNumberDto) {
        boolean result = userRepository.existsByEmail(reqSignUpNumberDto.getEmail());

        if (result) {
            throw new ExistsEmailException();
        }

        String number = createAuthenticationNumber();

        emailService.sendAuthenticationNumberMail(reqSignUpNumberDto.getEmail(),
                MailForm.SIGN_UP.getSubject(),
                MailForm.SIGN_UP.getContent() + number);

        SignUpNumber signUpNumber = signUpNumberRepository.findById(reqSignUpNumberDto.getEmail())
                .orElse(createSignUpNumber(reqSignUpNumberDto));

        signUpNumber.changeNumber(number);

        signUpNumberRepository.save(signUpNumber);
    }

    public void sendFindPasswordNumber(ReqFindPasswordNumberDto reqFindPasswordNumberDto) {
        boolean result =
                userRepository.existsByEmailAndUserId(reqFindPasswordNumberDto.getEmail(), reqFindPasswordNumberDto.getUserId());

        if (!result) {
            throw new IsNotExistsUserException();
        }

        String number = createAuthenticationNumber();

        emailService.sendAuthenticationNumberMail(reqFindPasswordNumberDto.getEmail(),
                MailForm.FIND_PASSWORD.getSubject(),
                MailForm.FIND_PASSWORD.getContent() + number);

        String id = reqFindPasswordNumberDto.getUserId() + reqFindPasswordNumberDto.getEmail();

        FindPasswordNumber findPasswordNumber = findPasswordNumberRepository.findById(id)
                .orElse(createFindPasswordNumber(reqFindPasswordNumberDto));

        findPasswordNumber.changeNumber(number);

        findPasswordNumberRepository.save(findPasswordNumber);
    }

    private String createAuthenticationNumber() {
        return RandomNumber.generateNumber().toString();
    }

    private SignUpNumber createSignUpNumber(ReqSignUpNumberDto reqSignUpNumberDto) {
        return SignUpNumber.builder()
                .email(reqSignUpNumberDto.getEmail())
                .build();
    }

    private FindPasswordNumber createFindPasswordNumber(ReqFindPasswordNumberDto reqFindPasswordNumberDto) {
        return FindPasswordNumber.builder()
                .email(reqFindPasswordNumberDto.getEmail())
                .userId(reqFindPasswordNumberDto.getUserId())
                .build();
    }

}
