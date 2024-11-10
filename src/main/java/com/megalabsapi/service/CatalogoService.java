package com.megalabsapi.service;

import com.megalabsapi.model.entity.Producto;

import java.util.List;

public interface CatalogoService {
    List<Producto> obtenerProductosEnCatalogo();
}

