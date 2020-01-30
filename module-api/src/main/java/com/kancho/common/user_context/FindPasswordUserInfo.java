package com.kancho.common.user_context;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindPasswordUserInfo {
    private String email;
    private String userId;

    @Builder
    private FindPasswordUserInfo (String email, String userId) {
        this.email = email;
        this.userId = userId;
    }
}
