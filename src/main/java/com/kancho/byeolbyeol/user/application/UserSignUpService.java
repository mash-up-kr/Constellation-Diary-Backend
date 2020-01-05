package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.dto.ReqEmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpService {
    private final UserAuthenticationEmailService userAuthenticationEmailService;

    public void generateAuthenticationNumber(ReqEmailDto reqEmailDto) {
        userAuthenticationEmailService.generateAuthenticationNumber(reqEmailDto);
    }
}
