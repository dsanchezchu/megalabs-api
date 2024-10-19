package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Entrevista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EntrevistaRepository extends JpaRepository<Entrevista, Integer> {
    List<Entrevista> findByRepresentanteDni(String dniRepresentante);
    List<Entrevista> findByFecha(Date fecha);
    List<Entrevista> findByLugarSede(String lugarSede);
    List<Entrevista> findByClienteRuc(String rucCliente);
}