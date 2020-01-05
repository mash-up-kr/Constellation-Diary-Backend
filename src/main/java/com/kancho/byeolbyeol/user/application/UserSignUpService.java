package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.dto.ReqEmailDto;
import com.kancho.byeolbyeol.user.dto.ResRegisterTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpService {
    private final UserAuthenticationEmailService userAuthenticationEmailService;
    private final UserAuthenticationNumberService userAuthenticationNumberService;

    public void generateAuthenticationNumber(ReqEmailDto reqEmailDto) {
        userAuthenticationEmailService.generateAuthenticationNumber(reqEmailDto);
    }

    public ResRegisterTokenDto validation(String email, Long number) {
        return userAuthenticationNumberService.validation(email ,number);
    }
}
