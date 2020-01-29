package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.user.domain.find_password_number.FindPasswordNumber;
import com.kancho.byeolbyeol.user.domain.find_password_number.FindPasswordNumberRepository;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumber;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumberRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationFindPasswordNumberDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationSignUpNumberDto;
import com.kancho.byeolbyeol.user.dto.response.ResAuthenticationTokenDto;
import com.kancho.byeolbyeol.user.exception.FailAuthenticationNumberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final SignUpNumberRepository signUpNumberRepository;
    private final FindPasswordNumberRepository findPasswordNumberRepository;
    private final JWTManager jwtManager;

    public ResAuthenticationTokenDto verifySignUpNumber(ReqValidationSignUpNumberDto reqValidationSignUpNumberDto) {

        SignUpNumber signUpNumber = signUpNumberRepository.findById(reqValidationSignUpNumberDto.getEmail())
                .orElseThrow(FailAuthenticationNumberException::new);

        if (signUpNumber.isNotEqualNumber(reqValidationSignUpNumberDto.getNumber())) {
            throw new FailAuthenticationNumberException();
        }

        String token = jwtManager.createSignUpToken(signUpNumber.getId());

        signUpNumberRepository.delete(signUpNumber);

        return ResAuthenticationTokenDto.builder()
                .token(token)
                .build();
    }

    public ResAuthenticationTokenDto verifyFindPasswordNumber(
            ReqValidationFindPasswordNumberDto reqValidationFindPasswordNumberDto) {

        String id = reqValidationFindPasswordNumberDto.getEmail() + reqValidationFindPasswordNumberDto.getUserId();

        FindPasswordNumber findPasswordNumber = findPasswordNumberRepository.findById(id)
                .orElseThrow(FailAuthenticationNumberException::new);

        if (findPasswordNumber.isNotEqualNumber(reqValidationFindPasswordNumberDto.getNumber())) {
            throw new FailAuthenticationNumberException();
        }

        String token = jwtManager.createFindPasswordToken(findPasswordNumber.getUserId(), findPasswordNumber.getEmail());

        findPasswordNumberRepository.delete(findPasswordNumber);

        return ResAuthenticationTokenDto.builder()
                .token(token)
                .build();

    }
}
