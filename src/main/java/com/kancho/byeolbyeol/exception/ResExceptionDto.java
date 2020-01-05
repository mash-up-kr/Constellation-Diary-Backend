package com.kancho.byeolbyeol.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResExceptionDto {

    private ErrorModel error;

    @Builder
    private ResExceptionDto(ErrorModel errorModel) {
        this.error = errorModel;
    }
}
