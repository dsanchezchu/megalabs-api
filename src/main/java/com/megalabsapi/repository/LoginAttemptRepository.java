package com.megalabsapi.repository;

import com.megalabsapi.model.entity.LoginAttempt;
import com.megalabsapi.model.entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

    Optional<LoginAttempt> findTopByRepresentanteOrderByAttemptTimeDesc(Representante representante);

    List<LoginAttempt> findByRepresentanteAndIsSuspiciousTrue(Representante representante);
}



