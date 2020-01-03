package com.kancho.byeolbyeol.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ReqEmailDto {

    @NotNull
    private String email;
}
