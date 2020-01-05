package com.kancho.byeolbyeol.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResUserDto {

    private Long id;
    private String userId;
    private String constellation;

    @Builder
    private ResUserDto(Long id, String userId, String constellation) {
        this.id = id;
        this.userId = userId;
        this.constellation = constellation;
    }
}
