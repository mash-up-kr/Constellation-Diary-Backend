package com.kancho.byeolbyeol.user.domain.sign_up_numbers;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "sign_up_numbers")
@Entity
public class SignUpNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String number;

    private Long expirationTime;

    @Builder
    private SignUpNumber(String email, String number,
                         Long expirationTime) {
        this.email = email;
        this.number = number;
        this.expirationTime = expirationTime;
    }

    public boolean isNotEqualNumber(Long number) {
        return !this.number.equals(number.toString());
    }
}
