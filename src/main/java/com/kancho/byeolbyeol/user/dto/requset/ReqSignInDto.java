package com.kancho.byeolbyeol.user.dto.requset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqSignInDto {
    @NotNull
    private String userId;

    @NotNull
    private String password;

    @NotNull
    private String fcmToken;
}
