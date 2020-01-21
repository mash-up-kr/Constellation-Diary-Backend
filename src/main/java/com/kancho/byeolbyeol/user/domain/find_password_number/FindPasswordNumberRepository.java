package com.kancho.byeolbyeol.user.domain.find_password_number;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FindPasswordNumberRepository extends JpaRepository<FindPasswordNumber, Long> {

    Optional<FindPasswordNumber>
    findFirstByEmailAndUserIdAndExpirationTimeGreaterThanEqualOrderByExpirationTimeDesc(
            String email, String userId, long currentTimeMillis);
}
