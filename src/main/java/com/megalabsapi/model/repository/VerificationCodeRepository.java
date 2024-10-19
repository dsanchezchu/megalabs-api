package com.megalabsapi.model.repository;

import com.megalabsapi.model.entity.VerificationCode;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, String> {

    VerificationCode findByCodeAndRepresentanteDni(@NotEmpty(message = "El código de verificación es obligatorio") String code, @NotEmpty(message = "El DNI del representante es obligatorio") String representanteDni);
}
