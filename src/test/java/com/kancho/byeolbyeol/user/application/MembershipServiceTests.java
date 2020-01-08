package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.constellation.domain.ConstellationRepository;
import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpDto;
import com.kancho.byeolbyeol.common.exception.NotFoundConstellationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MembershipServiceTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserRepository userRepository;
    private ConstellationRepository constellationRepository;
    private JWTManager jwtManager;
    private MembershipService membershipService;
    private ReqSignUpDto reqSignUpDto;

    @Before
    public void mockUp() {
        userRepository = mock(UserRepository.class);
        constellationRepository = mock(ConstellationRepository.class);
        jwtManager = mock(JWTManager.class);
        reqSignUpDto = mock(ReqSignUpDto.class);

        membershipService = new MembershipService(userRepository, constellationRepository, jwtManager);
    }

    @Test
    public void 존재하지_않는_별자리로_요청할_경우() {
        when(constellationRepository.findByName(any())).thenReturn(Optional.empty());

        expectedException.expect(NotFoundConstellationException.class);

        membershipService.signUp(reqSignUpDto);
    }
}
