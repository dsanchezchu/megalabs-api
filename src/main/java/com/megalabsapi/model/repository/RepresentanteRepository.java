package com.megalabsapi.model.repository;

import com.megalabsapi.model.entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepresentanteRepository extends JpaRepository<Representante, String> {
    Representante findByDni(String dni);

    Representante findByEmail(String email);

    Representante findByEmailOrDni(String email, String dni);
}

