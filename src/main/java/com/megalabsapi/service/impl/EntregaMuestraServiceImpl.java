package com.megalabsapi.service.impl;

import com.megalabsapi.dto.EntregaMuestraDTO;
import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.service.EntregaMuestraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntregaMuestraServiceImpl implements EntregaMuestraService {

    private final EntregaMuestraRepository entregaMuestraRepository;

    @Override
    public List<EntregaMuestraDTO> getAll() {
        return entregaMuestraRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EntregaMuestraDTO> paginate(Pageable pageable) {
        return entregaMuestraRepository.findAll(pageable).map(this::toDTO);
    }

    @Override
    public EntregaMuestraDTO findById(Integer id) {
        Entrega_Muestra entrega = entregaMuestraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entrega no encontrada con ID: " + id));
        return toDTO(entrega);
    }

    @Override
    public EntregaMuestraDTO create(EntregaMuestraDTO entregaMuestraDTO) {
        Entrega_Muestra entrega = entregaMuestraRepository.save(toEntity(entregaMuestraDTO));
        return toDTO(entrega);
    }

    @Override
    public EntregaMuestraDTO update(Integer id, EntregaMuestraDTO entregaMuestraDTO) {
        Entrega_Muestra existingEntrega = entregaMuestraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entrega no encontrada con ID: " + id));

        existingEntrega.setLugar(entregaMuestraDTO.getLugar());
        existingEntrega.setFecha(entregaMuestraDTO.getFecha());
        existingEntrega.setEstado(entregaMuestraDTO.getEstado());
        Entrega_Muestra updatedEntrega = entregaMuestraRepository.save(existingEntrega);

        return toDTO(updatedEntrega);
    }

    @Override
    public void delete(Integer id) {
        entregaMuestraRepository.deleteById(id);
    }

    @Override
    public List<EntregaMuestraDTO> findByClienteRuc(String ruc) {
        return entregaMuestraRepository.findByClienteRuc(ruc).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntregaMuestraDTO> findByFecha(Date fecha) {
        return entregaMuestraRepository.findByFecha(fecha).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private EntregaMuestraDTO toDTO(Entrega_Muestra entrega) {
        return new EntregaMuestraDTO(
                entrega.getId(),
                entrega.getLugar(),
                entrega.getFecha(),
                entrega.getEstado(),
                entrega.getCreatedAt(),
                entrega.getUpdatedAt()
        );
    }

    private Entrega_Muestra toEntity(EntregaMuestraDTO dto) {
        Entrega_Muestra entrega = new Entrega_Muestra();
        entrega.setId(dto.getIdEntrega());
        entrega.setLugar(dto.getLugar());
        entrega.setFecha(dto.getFecha());
        entrega.setEstado(dto.getEstado());
        return entrega;
    }
}
