package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Los métodos CRUD estándar ya están disponibles como findById, findAll, etc.
}