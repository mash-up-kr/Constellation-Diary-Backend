package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.common.JWTManager;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumber;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumberRepository;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationPurpose;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResRegisterTokenDto;
import com.kancho.byeolbyeol.user.exception.IsNotSameAuthenticationNumberException;
import com.kancho.byeolbyeol.user.exception.NotFoundAuthenticationNumberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationNumberRepository authenticationNumberRepository;
    private final JWTManager jwtManager;

    public ResRegisterTokenDto verify(ReqValidationNumberDto reqValidationNumberDto) {

        AuthenticationNumber authenticationNumber =
                authenticationNumberRepository
                        .findFirstByEmailAndAuthenticationPurposeAndExpirationTimeGreaterThanEqualOrderByExpirationTime(
                                reqValidationNumberDto.getEmail(),
                                AuthenticationPurpose.SIGN_UP,
                                System.currentTimeMillis())
                        .orElseThrow(NotFoundAuthenticationNumberException::new);

        if (authenticationNumber.isNotEqualNumber(reqValidationNumberDto.getNumber())) {
            throw new IsNotSameAuthenticationNumberException();
        }

        String registerToken = jwtManager.createRegisterToken(authenticationNumber.getEmail());

        authenticationNumberRepository.delete(authenticationNumber);

        return ResRegisterTokenDto.builder()
                .registerToken(registerToken)
                .build();
    }
}
