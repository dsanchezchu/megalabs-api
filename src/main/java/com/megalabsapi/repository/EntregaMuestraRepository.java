package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Entrega_Muestra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaMuestraRepository extends JpaRepository<Entrega_Muestra,Integer> {
    //Logica de acceso a base de datos para ejecutar las queries
    //Heredar el CRUD

}
