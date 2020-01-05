package com.kancho.byeolbyeol.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResSignUpDto {
    private ResTokenDto tokens;
    private ResUserDto user;

    @Builder
    private ResSignUpDto(ResTokenDto resTokenDto, ResUserDto resUserDto) {
        this.tokens = resTokenDto;
        this.user = resUserDto;
    }
}
