package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, String> {

    Optional<Representante> findByDni(String dni);

    Optional<Representante> findByEmail(String email);

    Optional<Representante> findByEmailOrDni(String email, String dni);

    boolean existsByDni(String dni);

    boolean existsByEmail(String email);
}

