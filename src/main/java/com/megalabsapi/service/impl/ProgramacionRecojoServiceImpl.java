package com.megalabsapi.service.impl;

import com.megalabsapi.dto.ProgramacionRecojoDTO;
import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.model.entity.ProgramacionRecojo;
import com.megalabsapi.repository.ClienteRepository;
import com.megalabsapi.repository.ProgramacionRecojoRepository;
import com.megalabsapi.service.ProgramacionRecojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramacionRecojoServiceImpl implements ProgramacionRecojoService {

    @Autowired
    private ProgramacionRecojoRepository programacionRecojoRepository;

    @Autowired
    private ClienteRepository clienteRepository; // Repositorio para obtener cliente por RUC

    @Override
    public List<ProgramacionRecojoDTO> obtenerProgramacionRecojo() {
        List<ProgramacionRecojo> programaciones = programacionRecojoRepository.findAll();
        return programaciones.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void crearProgramacionRecojo(ProgramacionRecojoDTO programacionDTO) {
        // Buscar al cliente en la base de datos
        Cliente cliente = clienteRepository.findById(programacionDTO.getClienteRuc())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con RUC: " + programacionDTO.getClienteRuc()));

        ProgramacionRecojo programacion = new ProgramacionRecojo();
        programacion.setFechaRecojo(programacionDTO.getFechaRecojo());
        programacion.setNombreMuestra(programacionDTO.getNombreMuestra());
        programacion.setCantidadMuestra(programacionDTO.getCantidadMuestra());
        programacion.setConfirmado(false);
        programacion.setCliente(cliente); // Asigna el cliente encontrado a la programación

        programacionRecojoRepository.save(programacion);
    }

    @Override
    public void confirmarRecojo(Long id) {
        ProgramacionRecojo programacion = programacionRecojoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Programación no encontrada"));
        programacion.setConfirmado(true);
        programacionRecojoRepository.save(programacion);
    }

    @Override
    public List<ProgramacionRecojoDTO> obtenerProgramacionPorRuc(String ruc) {
        List<ProgramacionRecojo> programaciones = programacionRecojoRepository.findByClienteRuc(ruc);
        return programaciones.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private ProgramacionRecojoDTO convertirADTO(ProgramacionRecojo programacion) {
        ProgramacionRecojoDTO dto = new ProgramacionRecojoDTO();
        dto.setFechaRecojo(programacion.getFechaRecojo());
        dto.setNombreMuestra(programacion.getNombreMuestra());
        dto.setCantidadMuestra(programacion.getCantidadMuestra());
        dto.setConfirmado(programacion.getConfirmado());
        dto.setClienteRuc(programacion.getCliente().getRuc());
        dto.setNombreCliente(programacion.getCliente().getNombre());
        return dto;
    }
}
