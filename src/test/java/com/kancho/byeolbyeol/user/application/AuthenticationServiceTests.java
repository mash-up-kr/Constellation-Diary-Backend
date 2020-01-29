package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.user.domain.find_password_number.FindPasswordNumberRepository;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumber;
import com.kancho.byeolbyeol.user.domain.sign_up_numbers.SignUpNumberRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqValidationSignUpNumberDto;
import com.kancho.byeolbyeol.user.exception.FailAuthenticationNumberException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private AuthenticationService authenticationService;
    private SignUpNumberRepository signUpNumberRepository;
    private FindPasswordNumberRepository findPasswordNumberRepository;
    private JWTManager jwtManager;
    private SignUpNumber signUpNumber;
    private ReqValidationSignUpNumberDto reqValidationSignUpNumberDto;

    @Before
    public void mockUp() {
        signUpNumber = mock(SignUpNumber.class);
        signUpNumberRepository = mock(SignUpNumberRepository.class);
        findPasswordNumberRepository = mock(FindPasswordNumberRepository.class);
        jwtManager = mock(JWTManager.class);
        authenticationService =
                new AuthenticationService(signUpNumberRepository, findPasswordNumberRepository, jwtManager);

        reqValidationSignUpNumberDto = new ReqValidationSignUpNumberDto(123456L, "test@naver.com");
    }

    @Test
    public void 인증_번호가_존재하지_않을_경우() {
        when(signUpNumberRepository.findById(any())).thenReturn(Optional.empty());

        expectedException.expect(FailAuthenticationNumberException.class);

        authenticationService.verifySignUpNumber(reqValidationSignUpNumberDto);
    }

    @Test
    public void 인증_번호가_같지_않을_경우() {
        when(signUpNumberRepository.findById(any())).thenReturn(Optional.ofNullable(signUpNumber));
        when(signUpNumber.isNotEqualNumber(any())).thenReturn(true);

        expectedException.expect(FailAuthenticationNumberException.class);

        authenticationService.verifySignUpNumber(reqValidationSignUpNumberDto);
    }
}
