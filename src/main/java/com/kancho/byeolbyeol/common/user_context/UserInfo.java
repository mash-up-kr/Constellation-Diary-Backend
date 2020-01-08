package com.kancho.byeolbyeol.common.user_context;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfo {

    private Long id;
    private String userId;

    @Builder
    private UserInfo (Long id, String userId) {
        this.id = id;
        this.userId = userId;
    }
}
