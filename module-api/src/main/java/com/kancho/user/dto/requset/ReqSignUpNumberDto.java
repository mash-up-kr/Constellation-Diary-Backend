package com.kancho.user.dto.requset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqSignUpNumberDto {

    @NotNull
    @Email
    private String email;
}
