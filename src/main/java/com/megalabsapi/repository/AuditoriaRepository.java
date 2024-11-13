package com.megalabsapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.megalabsapi.model.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
}