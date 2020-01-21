package com.kancho.byeolbyeol.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResAuthenticationTokenDto {
    private String token;

    @Builder
    private ResAuthenticationTokenDto(String token) {
        this.token = token;
    }
}
