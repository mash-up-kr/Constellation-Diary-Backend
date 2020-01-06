package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.dto.requset.ReqAuthenticationNumbersDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResRegisterTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationNumberService {
    private final AuthenticationEmailService authenticationEmailService;
    private final AuthenticationService authenticationService;

    public void sendAuthenticationNumber(ReqAuthenticationNumbersDto reqAuthenticationNumbersDto) {
        authenticationEmailService.sendAuthenticationNumber(reqAuthenticationNumbersDto);
    }

    public ResRegisterTokenDto verify(ReqValidationNumberDto reqValidationNumberDto) {
        return authenticationService.verify(reqValidationNumberDto);
    }
}
