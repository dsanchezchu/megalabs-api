package com.megalabsapi.repository;

import com.megalabsapi.model.entity.ProgramacionRecojo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramacionRecojoRepository extends JpaRepository<ProgramacionRecojo, Long> {
    List<ProgramacionRecojo> findByClienteRuc(String ruc);
}

