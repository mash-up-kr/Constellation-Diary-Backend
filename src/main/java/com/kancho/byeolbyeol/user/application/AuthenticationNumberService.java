package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.dto.requset.ReqEmailDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResRegisterTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationNumberService {
    private final AuthenticationNumberEmailService authenticationNumberEmailService;
    private final AuthenticationService authenticationService;

    public void generateAuthenticationNumber(ReqEmailDto reqEmailDto) {
        authenticationNumberEmailService.generateAuthenticationNumber(reqEmailDto);
    }

    public ResRegisterTokenDto validation(ReqValidationNumberDto reqValidationNumberDto) {
        return authenticationService.validation(reqValidationNumberDto);
    }
}
