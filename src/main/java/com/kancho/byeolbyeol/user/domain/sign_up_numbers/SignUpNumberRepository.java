package com.kancho.byeolbyeol.user.domain.sign_up_numbers;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface SignUpNumberRepository extends JpaRepository<SignUpNumber, Long> {
    Optional<SignUpNumber>
    findFirstByEmailAndExpirationTimeGreaterThanEqualOrderByExpirationTimeDesc(
            String email, Long currentTimeMillis);

    @Transactional
    void deleteByEmailAndExpirationTimeLessThanEqual(
            String email, Long currentTimeMillis);
}
