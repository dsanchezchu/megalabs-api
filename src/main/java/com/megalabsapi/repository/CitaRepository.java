package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Cita;
import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.model.entity.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByCliente(Cliente cliente);
    List<Cita> findByRepresentante(Representante representante);
    boolean existsByClienteAndFechaHora(Cliente cliente, LocalDateTime fechaHora);
    boolean existsByRepresentanteAndFechaHora(Representante representante, LocalDateTime fechaHora);
}