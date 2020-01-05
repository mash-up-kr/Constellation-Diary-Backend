package com.kancho.byeolbyeol.user.domain.authenticationnumber;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationNumberRepository extends JpaRepository<AuthenticationNumber, Long> {
    Optional<AuthenticationNumber>
    findFirstByEmailAndAuthenticationPurposeAndExpirationTimeGreaterThanEqualOrderByExpirationTime(
            String email, AuthenticationPurpose authenticationPurpose, Long currentTimeMillis);
}
