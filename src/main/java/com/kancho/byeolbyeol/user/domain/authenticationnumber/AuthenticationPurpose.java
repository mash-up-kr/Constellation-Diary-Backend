package com.kancho.byeolbyeol.user.domain.authenticationnumber;

import com.kancho.byeolbyeol.user.exception.NotFoundAuthenticationPurposeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AuthenticationPurpose {

    FIND_ID("아이디 찾기"),
    SIGN_UP("회원가입");

    private String value;

    AuthenticationPurpose(String value) {
        this.value = value;
    }

    public static AuthenticationPurpose findByAuthenticationPurpose(String value) {
        return Arrays.stream(AuthenticationPurpose.values())
                .filter(approvalProgress -> approvalProgress.hasValue(value))
                .findAny()
                .orElseThrow(NotFoundAuthenticationPurposeException::new);
    }

    private boolean hasValue(String value) {
        return this.toString().equals(value);
    }
}
