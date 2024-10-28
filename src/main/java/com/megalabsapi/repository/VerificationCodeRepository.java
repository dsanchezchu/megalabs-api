package com.megalabsapi.repository;

import com.megalabsapi.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    Optional<VerificationCode> findByRepresentanteEmailAndIsUsedFalse(String email);

    Optional<VerificationCode> findByCodeAndRepresentanteDni(String code, String representanteDni);
}
