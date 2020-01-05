package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumber;
import com.kancho.byeolbyeol.user.domain.authenticationnumber.AuthenticationNumberRepository;
import com.kancho.byeolbyeol.user.exception.IsNotSameAuthenticationNumberException;
import com.kancho.byeolbyeol.user.exception.NotFoundAuthenticationNumberException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAuthenticationNumberServiceTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserAuthenticationNumberService userAuthenticationNumberService;
    private AuthenticationNumberRepository authenticationNumberRepository;
    private JWTManager jwtManager;
    private AuthenticationNumber authenticationNumber;

    @Before
    public void mockUp() {
        authenticationNumber = mock(AuthenticationNumber.class);
        authenticationNumberRepository = mock(AuthenticationNumberRepository.class);
        jwtManager = mock(JWTManager.class);
        userAuthenticationNumberService =
                new UserAuthenticationNumberService(authenticationNumberRepository, jwtManager);
    }

    @Test
    public void 인증_번호가_존재하지_않을_경우() {
        when(authenticationNumberRepository
                .findFirstByEmailAndExpirationTimeGreaterThanEqualOrderByExpirationTime(any(), any()))
                .thenReturn(Optional.empty());

        expectedException.expect(NotFoundAuthenticationNumberException.class);

        userAuthenticationNumberService.validation("test@naver.com", 123456L);
    }

    @Test
    public void 인증_번호가_같지_않을_경우() {
        when(authenticationNumberRepository
                .findFirstByEmailAndExpirationTimeGreaterThanEqualOrderByExpirationTime(any(), any()))
                .thenReturn(Optional.ofNullable(authenticationNumber));
        when(authenticationNumber.isNotEqualNumber(any())).thenReturn(true);

        expectedException.expect(IsNotSameAuthenticationNumberException.class);

        userAuthenticationNumberService.validation("test@naver.com", 123456L);
    }

}
