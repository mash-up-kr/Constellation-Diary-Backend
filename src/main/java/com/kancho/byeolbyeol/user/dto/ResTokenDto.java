package com.kancho.byeolbyeol.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResTokenDto {
    private String authenticationToken;
    private String refreshToken;

    @Builder
    private ResTokenDto(String authenticationToken, String refreshToken) {
        this.authenticationToken = authenticationToken;
        this.refreshToken = refreshToken;
    }
}
