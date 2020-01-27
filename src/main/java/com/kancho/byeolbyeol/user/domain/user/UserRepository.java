package com.kancho.byeolbyeol.user.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(String checkUserId);

    Optional<User> findByUserId(String userId);

    Optional<User> findByEmail(String email);

    boolean  existsByEmailAndUserId(String email, String userId);

    boolean  existsByEmail(String email);

    Optional<User> findByUserIdAndEmail(String userId, String email);
}
