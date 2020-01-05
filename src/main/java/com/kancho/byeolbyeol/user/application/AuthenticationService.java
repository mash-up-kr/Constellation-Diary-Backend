package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumber;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumberRepository;
import com.kancho.byeolbyeol.user.dto.ReqValidationNumberDto;
import com.kancho.byeolbyeol.user.dto.ResRegisterTokenDto;
import com.kancho.byeolbyeol.user.exception.IsNotSameAuthenticationNumberException;
import com.kancho.byeolbyeol.user.exception.NotFoundAuthenticationNumberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationNumberRepository authenticationNumberRepository;
    private final JWTManager jwtManager;

    public ResRegisterTokenDto validation(ReqValidationNumberDto reqValidationNumberDto) {

        AuthenticationNumber authenticationNumber =
                authenticationNumberRepository
                        .findFirstByEmailAndExpirationTimeGreaterThanEqualOrderByExpirationTime(
                                reqValidationNumberDto.getEmail(),
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
