package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Entrega_Muestra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface EntregaMuestraRepository extends JpaRepository<Entrega_Muestra, Integer> {
    List<Entrega_Muestra> findByClienteRuc(String ruc);
    List<Entrega_Muestra> findByFecha(Date fecha);
}
