package com.kancho.user.dto.requset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqSignUpDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String userId;

    @NotNull
    private String password;

    @NotNull
    private String constellation;
}
