package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.user.domain.user.UserRepository;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignInDto;
import com.kancho.byeolbyeol.user.dto.requset.ReqSignUpDto;
import com.kancho.byeolbyeol.user.dto.response.ResCheckUserDto;
import com.kancho.byeolbyeol.user.dto.response.ResTokenDto;
import com.kancho.byeolbyeol.user.dto.response.ResUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MembershipService membershipService;
    private final UserTokenService userTokenService;

    public ResCheckUserDto duplicateCheck(String checkUserId) {
        boolean result = userRepository.existsByUserId(checkUserId);

        return ResCheckUserDto.builder()
                .userId(checkUserId)
                .available(!result)
                .build();
    }

    public ResUserInfoDto signUp(ReqSignUpDto reqSignUpDto) {
        return membershipService.signUp(reqSignUpDto);
    }

    public ResUserInfoDto signIn(ReqSignInDto reqSignInDto) {
        return membershipService.signIn(reqSignInDto);
    }

    public ResTokenDto refreshToken(String token) {
        return userTokenService.refreshToken(token);
    }
}
