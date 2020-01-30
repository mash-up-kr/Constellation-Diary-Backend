package com.kancho.daily.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqWriteDiaryDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long horoscopeId;

    @NotNull
    private Date date;
}
