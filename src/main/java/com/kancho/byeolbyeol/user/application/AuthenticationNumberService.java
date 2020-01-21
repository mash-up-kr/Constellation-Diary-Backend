package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.dto.requset.ReqFindPasswordNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationFindPasswordNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationSignUpNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResFindPasswordTokenDto;
import com.kancho.byeolbyeol.user.dto.response.ResRegisterTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationNumberService {
    private final AuthenticationEmailService authenticationEmailService;
    private final AuthenticationService authenticationService;

    public void sendSignUpNumber(ReqSignUpNumberDto reqSignUpNumberDto) {
        authenticationEmailService.sendSignUpNumber(reqSignUpNumberDto);
    }

    public ResRegisterTokenDto verifySignUpNumber(ReqValidationSignUpNumberDto reqValidationSignUpNumberDto) {
        return authenticationService.verifySignUpNumber(reqValidationSignUpNumberDto);
    }

    public void sendFindPasswordNumber(ReqFindPasswordNumberDto reqFindPasswordNumberDto) {
        authenticationEmailService.sendFindPasswordNumber(reqFindPasswordNumberDto);
    }

    public ResFindPasswordTokenDto verifyFindPasswordNumber(ReqValidationFindPasswordNumberDto reqValidationFindPasswordNumberDto) {
        return authenticationService.verifyFindPasswordNumber(reqValidationFindPasswordNumberDto);
    }
}
