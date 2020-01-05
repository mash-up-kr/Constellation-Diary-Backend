package com.kancho.byeolbyeol.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoDto {

    private Long id;
    private String userId;

    @Builder
    private UserInfoDto (Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }
}
