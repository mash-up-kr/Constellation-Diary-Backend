package com.kancho.user.domain.sign_up_numbers;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RedisHash(value = "sign_up_numbers", timeToLive = 180L)
public class SignUpNumber {

    @Id
    private String id;

    private String number;

    @Builder
    private SignUpNumber(String email, String number) {
        this.id = email;
        this.number = number;
    }

    public void changeNumber(String number) {
        this.number = number;
    }

    public boolean isNotEqualNumber(Long number) {
        return !this.number.equals(number.toString());
    }
}
