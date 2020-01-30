package com.kancho.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResCheckUserDto {

    private String userId;
    private boolean available;

    @Builder
    private ResCheckUserDto(String userId, boolean available) {
        this.userId = userId;
        this.available = available;
    }
}
