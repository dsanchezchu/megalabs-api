package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Entrega_Muestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EntregaMuestraRepository extends JpaRepository<Entrega_Muestra, Integer> {
    List<Entrega_Muestra> findByClienteRuc(String ruc);
}