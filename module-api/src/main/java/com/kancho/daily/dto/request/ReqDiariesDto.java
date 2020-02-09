package com.kancho.daily.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqDiariesDto {

    @NotEmpty
    List<Long> diaryIds;
}
