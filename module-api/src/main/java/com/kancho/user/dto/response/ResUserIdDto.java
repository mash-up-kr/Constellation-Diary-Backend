package com.kancho.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResUserIdDto {

    private String userId;

    @Builder
    private ResUserIdDto(String userId) {
        this.userId = userId;
    }
}
