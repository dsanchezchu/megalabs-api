package com.megalabsapi.repository;

import com.megalabsapi.entity.LoginAttempt;
import com.megalabsapi.entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
    List<LoginAttempt> findByRepresentanteAndIsSuspiciousTrue(Representante representante);
    LoginAttempt findTopByRepresentanteOrderByAttemptTimeDesc(Representante representante);
}

