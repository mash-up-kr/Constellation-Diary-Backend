package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumber;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumberRepository;
import com.kancho.byeolbyeol.user.dto.ResRegisterTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationNumberService {

    private final AuthenticationNumberRepository authenticationNumberRepository;
    private final JWTManager jwtManager;

    public ResRegisterTokenDto validation(String email, Long number) {
        //TODO Exception 처리
        AuthenticationNumber authenticationNumber = authenticationNumberRepository
                .findFirstByEmailAndExpirationTimeLessThanEqualOrderByExpirationTime(email,
                        System.currentTimeMillis())
                .orElseThrow(RuntimeException::new);

        //TODO Exception 처리
        if (authenticationNumber.isNotEqualNumber(number)) {
            throw new RuntimeException();
        }

        String registerToken = jwtManager.createRegisterToken(authenticationNumber.getEmail());

        return ResRegisterTokenDto.builder()
                .registerToken(registerToken)
                .build();
    }
}
