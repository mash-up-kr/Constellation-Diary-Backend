package com.kancho.byeolbyeol.user.domain.find_password_number;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "find_password_numbers")
@Entity
public class FindPasswordNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String userId;

    private String number;

    private Long expirationTime;

    @Builder
    private FindPasswordNumber(String email, String userId, String number,
                         Long expirationTime) {
        this.email = email;
        this.userId = userId;
        this.number = number;
        this.expirationTime = expirationTime;
    }

    public boolean isNotEqualNumber(Long number) {
        return !this.number.equals(number.toString());
    }
}
