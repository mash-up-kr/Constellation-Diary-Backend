package com.kancho.byeolbyeol.user.dto.requset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqFindUserIdDto {

    @NotNull
    private Long number;

    @NotNull
    private Long email;
}
