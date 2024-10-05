package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.enums.EntregaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EntregaMuestraRepository extends JpaRepository<Entrega_Muestra,Integer> {
    //Logica de acceso a base de datos para ejecutar las queries
    //Heredar el CRUD
    List<Entrega_Muestra> findByFecha(Date fecha);
    List<Entrega_Muestra> findByClienteRuc(String  RUC);
}
