package com.kancho.byeolbyeol.user.dto.requset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqAuthenticationNumbersDto {

    @NotNull
    private String email;

    @NotNull
    private String authenticationPurpose;
}
