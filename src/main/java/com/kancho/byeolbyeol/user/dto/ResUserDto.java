package com.kancho.byeolbyeol.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResUserDto {

    private ResTokenDto tokens;
    private Long id;
    private String userId;
    private String constellation;

    @Builder
    private ResUserDto(ResTokenDto resTokenDto, Long id, String userId, String constellation) {
        this.tokens = resTokenDto;
        this.id = id;
        this.userId = userId;
        this.constellation = constellation;
    }
}
