package com.megalabsapi.service.impl;

import com.megalabsapi.dto.AuditoriaDTO;
import com.megalabsapi.model.entity.Auditoria;
import com.megalabsapi.repository.AuditoriaRepository;
import com.megalabsapi.service.AuditoriaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Override
    public void registrarAccion(String accion, String pantalla, String usuario) {
        Auditoria auditoria = new Auditoria();
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setAccion(accion);
        auditoria.setPantalla(pantalla);
        auditoria.setUsuario(usuario);
        auditoriaRepository.save(auditoria);
    }

    @Override
    public List<AuditoriaDTO> obtenerAuditorias() {
        List<Auditoria> auditorias = auditoriaRepository.findAll();
        return auditorias.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private AuditoriaDTO convertirADTO(Auditoria auditoria) {
        AuditoriaDTO dto = new AuditoriaDTO();
        dto.setId(auditoria.getId());
        dto.setFechaHora(auditoria.getFechaHora());
        dto.setAccion(auditoria.getAccion());
        dto.setPantalla(auditoria.getPantalla());
        dto.setUsuario(auditoria.getUsuario());
        return dto;
    }
}

