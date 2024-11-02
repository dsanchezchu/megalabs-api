package com.megalabsapi.repository;

import com.megalabsapi.model.entity.MetodoAnalitico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoAnaliticoRepository extends JpaRepository<MetodoAnalitico, Integer> {
    // Puedes agregar métodos personalizados si necesitas filtrado adicional
}

