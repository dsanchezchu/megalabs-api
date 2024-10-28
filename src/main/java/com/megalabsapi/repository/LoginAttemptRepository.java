package com.megalabsapi.repository;

import com.megalabsapi.entity.LoginAttempt;
import com.megalabsapi.entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

    Optional<LoginAttempt> findTopByRepresentanteOrderByAttemptTimeDesc(Representante representante);

    List<LoginAttempt> findByRepresentanteAndIsSuspiciousTrue(Representante representante);
}



