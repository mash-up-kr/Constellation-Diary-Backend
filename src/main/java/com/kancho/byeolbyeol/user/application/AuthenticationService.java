package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumber;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumberRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationFindPasswordNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationSignUpNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResFindPasswordTokenDto;
import com.kancho.byeolbyeol.user.dto.response.ResRegisterTokenDto;
import com.kancho.byeolbyeol.user.exception.IsNotSameAuthenticationNumberException;
import com.kancho.byeolbyeol.user.exception.NotFoundAuthenticationNumberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SignUpNumberRepository signUpNumberRepository;
    private final JWTManager jwtManager;

    public ResRegisterTokenDto verifySignUpNumber(ReqValidationSignUpNumberDto reqValidationSignUpNumberDto) {

        SignUpNumber signUpNumber =
                signUpNumberRepository
                        .findFirstByEmailAndExpirationTimeGreaterThanEqualOrderByExpirationTimeDesc(
                                reqValidationSignUpNumberDto.getEmail(),
                                System.currentTimeMillis())
                        .orElseThrow(NotFoundAuthenticationNumberException::new);

        if (signUpNumber.isNotEqualNumber(reqValidationSignUpNumberDto.getNumber())) {
            throw new IsNotSameAuthenticationNumberException();
        }

        String registerToken = jwtManager.createRegisterToken(signUpNumber.getEmail());

        signUpNumberRepository.
                deleteByEmailAndExpirationTimeLessThanEqual(
                        signUpNumber.getEmail(), signUpNumber.getExpirationTime());

        return ResRegisterTokenDto.builder()
                .registerToken(registerToken)
                .build();
    }

    public ResFindPasswordTokenDto verifyFindPasswordNumber(ReqValidationFindPasswordNumberDto reqValidationFindPasswordNumberDto) {

        return new ResFindPasswordTokenDto();

    }
}
