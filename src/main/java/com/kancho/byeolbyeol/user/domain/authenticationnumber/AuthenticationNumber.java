package com.kancho.byeolbyeol.user.domain.authenticationnumber;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class AuthenticationNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String number;

    private Long expirationTime;

    @Builder
    private AuthenticationNumber(String email, String number, Long expirationTime) {
        this.email = email;
        this.number = number;
        this.expirationTime = expirationTime;
    }
}
