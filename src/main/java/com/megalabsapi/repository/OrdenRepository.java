package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Orden;
import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.model.enums.EstadoOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {

    // Consulta personalizada para obtener todas las órdenes de un cliente específico
    List<Orden> findByCliente(Cliente cliente);

    // Consulta personalizada para obtener órdenes por estado
    List<Orden> findByEstado(EstadoOrden estado);
}
