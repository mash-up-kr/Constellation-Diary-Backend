package com.kancho.byeolbyeol.user.domain.authenticationnumber;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AuthenticationNumberRepository extends JpaRepository<AuthenticationNumber, Long> {
    Optional<AuthenticationNumber>
    findFirstByEmailAndExpirationTimeGreaterThanEqualOrderByExpirationTimeDesc(
            String email, Long currentTimeMillis);

    @Transactional
    void deleteByEmailAndExpirationTimeLessThanEqual(
            String email, Long currentTimeMillis);
}
