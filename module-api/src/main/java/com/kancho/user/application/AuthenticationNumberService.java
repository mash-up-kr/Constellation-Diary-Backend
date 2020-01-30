package com.kancho.user.application;

import com.kancho.user.dto.requset.ReqFindPasswordNumberDto;
import com.kancho.user.dto.requset.ReqSignUpNumberDto;
import com.kancho.user.dto.requset.ReqValidationFindPasswordNumberDto;
import com.kancho.user.dto.requset.ReqValidationSignUpNumberDto;
import com.kancho.user.dto.response.ResAuthenticationTokenDto;
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

    public ResAuthenticationTokenDto verifySignUpNumber(ReqValidationSignUpNumberDto reqValidationSignUpNumberDto) {
        return authenticationService.verifySignUpNumber(reqValidationSignUpNumberDto);
    }

    public void sendFindPasswordNumber(ReqFindPasswordNumberDto reqFindPasswordNumberDto) {
        authenticationEmailService.sendFindPasswordNumber(reqFindPasswordNumberDto);
    }

    public ResAuthenticationTokenDto verifyFindPasswordNumber(ReqValidationFindPasswordNumberDto reqValidationFindPasswordNumberDto) {
        return authenticationService.verifyFindPasswordNumber(reqValidationFindPasswordNumberDto);
    }
}
