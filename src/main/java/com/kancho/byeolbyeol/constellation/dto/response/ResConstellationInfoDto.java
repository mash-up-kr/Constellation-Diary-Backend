package com.kancho.byeolbyeol.constellation.dto.response;

import com.kancho.byeolbyeol.constellation.domain.Constellation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ResConstellationInfoDto {

    @ApiModelProperty(position = 1, example = "1")
    private Integer id;

    @ApiModelProperty(position = 2, example = "물병자리")
    private String name;

    @ApiModelProperty(position = 3, example = "상식이나 인간관계에 얽매이지 않고 나아가는 자유로운 개혁자")
    private String description;

    @ApiModelProperty(position = 4, example = "1월 20일~2월 18일")
    private String date;

    @ApiModelProperty(position = 5)
    private String iconUrl;

    @ApiModelProperty(position = 6)
    private String imageUrl;

    public ResConstellationInfoDto(Constellation constellation) {
        this.id = constellation.getId();
        this.name = constellation.getName();
        this.description = constellation.getDescription();
        this.date = constellation.getDate();
        this.iconUrl = constellation.getIconUrl();
        this.imageUrl = constellation.getImageUrl();
    }
}