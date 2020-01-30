package com.kancho.user.domain.find_password_number;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RedisHash(value = "find_password_numbers", timeToLive = 180L)
public class FindPasswordNumber {

    @Id
    private String id;

    private String email;

    private String userId;

    private String number;

    @Builder
    private FindPasswordNumber(String email, String userId, String number) {
        this.id = email + userId;
        this.email = email;
        this.userId = userId;
        this.number = number;
    }

    public boolean isNotEqualNumber(Long number) {
        return !this.number.equals(number.toString());
    }

    public void changeNumber(String number) {
        this.number = number;
    }
}
