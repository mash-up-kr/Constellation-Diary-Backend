package com.kancho.byeolbyeol.user.dto.requset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqValidationFindPasswordNumberDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String userId;

    @NotNull
    private Long number;
}
