package com.kancho.byeolbyeol.user.domain.authenticationnumber;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationNumberRepository extends JpaRepository<AuthenticationNumber, Long> {
    Optional<AuthenticationNumber> findByEmailAndExpirationTimeLessThanEqual(String email, long currentTimeMillis);

    Optional<AuthenticationNumber> findFirstByEmailAndExpirationTimeLessThanEqualOrderByExpirationTime(String email,
                                                                                                       long currentTimeMillis);
}
