package com.megalabsapi.model.repository;

import com.megalabsapi.model.entity.PasswordRecoveryToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordRecoveryTokenRepository extends JpaRepository<PasswordRecoveryToken, Long> {
    Optional<PasswordRecoveryToken> findByToken(String token);
}

