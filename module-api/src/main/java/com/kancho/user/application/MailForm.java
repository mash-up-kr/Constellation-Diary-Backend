package com.kancho.user.application;

import lombok.Getter;

@Getter
public enum MailForm {
    SIGN_UP(" 회원가입 인증번호 : ", "별별일기 회원가입 인증번호입니다."),
    FIND_PASSWORD(" 비밀번호 찾기 인증번호 : ", "별별일기 비밀번호 찾기 인증번호입니다.");

    private String content;
    private String subject;

    MailForm(String content, String subject) {
        this.content = content;
        this.subject = subject;
    }
}
