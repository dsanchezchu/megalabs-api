package com.megalabsapi.repository;

import com.megalabsapi.model.entity.InsatisfaccionProducto;
import com.megalabsapi.model.enums.TipoQueja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsatisfaccionProductoRepository extends JpaRepository<InsatisfaccionProducto, Long> {
    List<InsatisfaccionProducto> findByTipoQueja(TipoQueja tipoQueja);
    List<InsatisfaccionProducto> findByDistribuidoFalse();
}