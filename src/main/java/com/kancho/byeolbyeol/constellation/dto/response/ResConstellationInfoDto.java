package com.kancho.byeolbyeol.constellation.dto.response;

import com.kancho.byeolbyeol.constellation.domain.Constellation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ResConstellationInfoDto {

    @ApiModelProperty(position = 1, example = "1")
    private Long id;

    @ApiModelProperty(position = 2, example = "물병자리")
    private String name;

    @ApiModelProperty(position = 3, example = "1월 20일~2월 18일")
    private String date;

    public ResConstellationInfoDto(Constellation constellation) {
        this.id = constellation.getId();
        this.name = constellation.getName();
        this.date = constellation.getDate();
    }
}