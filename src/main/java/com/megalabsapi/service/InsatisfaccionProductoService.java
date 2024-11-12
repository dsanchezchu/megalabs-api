package com.megalabsapi.service;

import com.megalabsapi.model.entity.InsatisfaccionProducto;
import com.megalabsapi.model.enums.TipoQueja;

import java.util.List;

public interface InsatisfaccionProductoService {
    InsatisfaccionProducto guardarInsatisfaccion(InsatisfaccionProducto insatisfaccion);
    List<InsatisfaccionProducto> obtenerTodasInsatisfacciones();
    List<InsatisfaccionProducto> obtenerPorTipoQueja(TipoQueja tipoQueja);
    void distribuirReporte();
}