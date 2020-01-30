package com.kancho.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResUserInfoDto {
    private ResTokenDto tokens;
    private ResUserDto user;

    @Builder
    private ResUserInfoDto(ResTokenDto resTokenDto, ResUserDto resUserDto) {
        this.tokens = resTokenDto;
        this.user = resUserDto;
    }
}
