package com.megalabsapi.service;

import com.megalabsapi.model.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> obtenerProductosEnDesabastecimiento();
}

