package com.kancho.byeolbyeol.user.application;

import com.kancho.byeolbyeol.authentication.JWTManager;
import com.kancho.byeolbyeol.common.user_context.UserInfo;
import com.kancho.byeolbyeol.user.dto.response.ResTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JWTManager jwtManager;

    public ResTokenDto refreshToken(UserInfo userInfo) {

        String authenticationToken =
                jwtManager.createRefreshToken(userInfo.getUserId(), userInfo.getId());
        String refreshToken =
                jwtManager.createAuthenticationToken(userInfo.getUserId(), userInfo.getId());

        return ResTokenDto.builder()
                .authenticationToken(authenticationToken)
                .refreshToken(refreshToken)
                .build();
    }
}
