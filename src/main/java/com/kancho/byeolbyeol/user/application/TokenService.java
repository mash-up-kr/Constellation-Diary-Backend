package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.common.JWTManager;
import com.kancho.byeolbyeol.common.UserInfoDto;
import com.kancho.byeolbyeol.user.dto.response.ResTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JWTManager jwtManager;

    public ResTokenDto refreshToken(String token) {

        UserInfoDto userInfoDto = jwtManager.getUserInfo(token);

        String authenticationToken =
                jwtManager.createRefreshToken(userInfoDto.getUserId(), userInfoDto.getId());
        String refreshToken =
                jwtManager.createAuthenticationToken(userInfoDto.getUserId(), userInfoDto.getId());

        return ResTokenDto.builder()
                .authenticationToken(authenticationToken)
                .refreshToken(refreshToken)
                .build();
    }
}
