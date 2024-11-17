package com.megalabsapi.service;

import com.megalabsapi.dto.EntregaMuestraDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EntregaMuestraService {
    List<EntregaMuestraDTO> getAll();

    Page<EntregaMuestraDTO> paginate(Pageable pageable);

    EntregaMuestraDTO findById(Integer id);

    EntregaMuestraDTO create(EntregaMuestraDTO entregaMuestraDTO);

    EntregaMuestraDTO update(Integer id, EntregaMuestraDTO entregaMuestraDTO);

    void delete(Integer id);

    List<EntregaMuestraDTO> findByClienteRuc(String ruc);

    List<EntregaMuestraDTO> findByFecha(Date fecha);
}
