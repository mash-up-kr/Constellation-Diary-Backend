package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.response.ResCheckUserDto;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService;
    private MembershipService membershipService;
    private UserRepository userRepository;
    private TokenService tokenService;
    private UserFindIdService userFindIdService;

    @Before
    public void mockUp() {
        userRepository = mock(UserRepository.class);
        membershipService = mock(MembershipService.class);
        tokenService = mock(TokenService.class);
        userFindIdService = mock(UserFindIdService.class);
        userService =
                new UserService(userRepository, membershipService, tokenService, userFindIdService);
    }

    @Test
    public void 유저의_아이디가_존재_할_경우() {
        when(userRepository.existsByUserId(any())).thenReturn(true);

        ResCheckUserDto resCheckUserDto = userService.duplicateCheck("testId");

        assertThat(resCheckUserDto.getUserId()).isEqualTo("testId");
        assertThat(resCheckUserDto.isAvailable()).isFalse();
    }

    @Test
    public void 유저의_아이디가_존재_하지_않을_경우() {
        when(userRepository.existsByUserId(any())).thenReturn(false);

        ResCheckUserDto resCheckUserDto = userService.duplicateCheck("testId");

        assertThat(resCheckUserDto.getUserId()).isEqualTo("testId");
        assertThat(resCheckUserDto.isAvailable()).isTrue();
    }
}