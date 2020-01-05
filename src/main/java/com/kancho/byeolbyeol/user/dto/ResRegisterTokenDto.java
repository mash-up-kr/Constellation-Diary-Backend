package com.kancho.byeolbyeol.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResRegisterTokenDto {
    private String registerToken;

    @Builder
    private ResRegisterTokenDto(String registerToken) {
        this.registerToken = registerToken;
    }
}
