package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.InsatisfaccionProducto;
import com.megalabsapi.model.enums.TipoQueja;
import com.megalabsapi.repository.InsatisfaccionProductoRepository;
import com.megalabsapi.service.InsatisfaccionProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsatisfaccionProductoServiceImpl implements InsatisfaccionProductoService {

    @Autowired
    private InsatisfaccionProductoRepository insatisfaccionProductoRepository;

    @Override
    public InsatisfaccionProducto guardarInsatisfaccion(InsatisfaccionProducto insatisfaccion) {
        return insatisfaccionProductoRepository.save(insatisfaccion);
    }

    @Override
    public List<InsatisfaccionProducto> obtenerTodasInsatisfacciones() {
        return insatisfaccionProductoRepository.findAll();
    }

    @Override
    public List<InsatisfaccionProducto> obtenerPorTipoQueja(TipoQueja tipoQueja) {
        return insatisfaccionProductoRepository.findByTipoQueja(tipoQueja);
    }

    @Override
    public void distribuirReporte() {
        List<InsatisfaccionProducto> noDistribuidos = insatisfaccionProductoRepository.findByDistribuidoFalse();
        // Lógica para distribución a áreas responsables
        noDistribuidos.forEach(i -> i.setDistribuido(true));
        insatisfaccionProductoRepository.saveAll(noDistribuidos);
    }
}