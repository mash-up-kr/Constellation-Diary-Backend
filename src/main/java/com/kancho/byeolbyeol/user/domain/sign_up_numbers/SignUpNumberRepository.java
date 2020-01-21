package com.kancho.byeolbyeol.user.domain.sign_up_numbers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignUpNumberRepository extends JpaRepository<SignUpNumber, Long> {
    Optional<SignUpNumber>
    findFirstByEmailAndExpirationTimeGreaterThanEqualOrderByExpirationTimeDesc(
            String email, Long currentTimeMillis);
}
