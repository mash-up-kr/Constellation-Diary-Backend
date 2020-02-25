package com.kancho.user.application;

import com.kancho.authentication.JWTManager;
import com.kancho.common.user_context.UserInfo;
import com.kancho.user.dto.response.ResTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JWTManager jwtManager;

    public ResTokenDto refreshToken(UserInfo userInfo) {

        String authenticationToken =
                jwtManager.createAuthenticationToken(userInfo.getUserId(), userInfo.getId());
        String refreshToken =
                jwtManager.createRefreshToken(userInfo.getUserId(), userInfo.getId());

        return ResTokenDto.builder()
                .authenticationToken(authenticationToken)
                .refreshToken(refreshToken)
                .build();
    }
}
