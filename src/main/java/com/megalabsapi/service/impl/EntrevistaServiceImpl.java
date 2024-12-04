package com.megalabsapi.service.impl;

import com.megalabsapi.dto.EntrevistaHisDTO;
import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.model.entity.Entrevista;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.repository.ClienteRepository;
import com.megalabsapi.repository.EntrevistaRepository;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.service.EntrevistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrevistaServiceImpl implements EntrevistaService {

    @Autowired
    private EntrevistaRepository entrevistaRepository;

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Método para convertir Entrevista a EntrevistaHisDTO
    private EntrevistaHisDTO convertirADTO(Entrevista entrevista) {
        EntrevistaHisDTO dto = new EntrevistaHisDTO();
        dto.setIdEntrevista(entrevista.getIdEntrevista());
        dto.setFecha(entrevista.getFecha());
        dto.setHora(entrevista.getHora());
        dto.setLugarSede(entrevista.getLugarSede());
        dto.setRepresentanteDni(entrevista.getRepresentante().getDni());
        dto.setClienteRuc(entrevista.getCliente().getRuc());
        return dto;
    }

    // Método para convertir EntrevistaHisDTO a Entrevista
    private Entrevista convertirAEntidad(EntrevistaHisDTO dto) {
        Entrevista entrevista = new Entrevista();
        entrevista.setIdEntrevista(dto.getIdEntrevista());
        entrevista.setFecha(dto.getFecha());
        entrevista.setHora(dto.getHora());
        entrevista.setLugarSede(dto.getLugarSede());

        Optional<Representante> representante = representanteRepository.findById(dto.getRepresentanteDni());
        representante.ifPresent(entrevista::setRepresentante);

        Optional<Cliente> cliente = clienteRepository.findById(dto.getClienteRuc());
        cliente.ifPresent(entrevista::setCliente);

        return entrevista;
    }

    @Override
    public List<EntrevistaHisDTO> obtenerEntrevistasPorRepresentante(String dniRepresentante) {
        return entrevistaRepository.findByRepresentanteDni(dniRepresentante)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public EntrevistaHisDTO crearEntrevista(EntrevistaHisDTO dto) {
        Entrevista entrevista = convertirAEntidad(dto);
        Entrevista nuevaEntrevista = entrevistaRepository.save(entrevista);
        return convertirADTO(nuevaEntrevista);
    }

    @Override
    public EntrevistaHisDTO actualizarEntrevista(Integer idEntrevista, EntrevistaHisDTO dto) {
        Entrevista entrevistaExistente = entrevistaRepository.findById(idEntrevista)
                .orElseThrow(() -> new RuntimeException("Entrevista no encontrada con ID: " + idEntrevista));
        Entrevista entrevistaActualizada = convertirAEntidad(dto);
        entrevistaActualizada.setIdEntrevista(idEntrevista);
        entrevistaActualizada = entrevistaRepository.save(entrevistaActualizada);
        return convertirADTO(entrevistaActualizada);
    }

    @Override
    public void eliminarEntrevista(Integer idEntrevista) {
        entrevistaRepository.deleteById(idEntrevista);
    }

    @Override
    public List<EntrevistaHisDTO> obtenerTodasLasEntrevistas() {
        return entrevistaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntrevistaHisDTO> obtenerEntrevistasPorFecha(Date fecha) {
        return entrevistaRepository.findByFecha(fecha)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntrevistaHisDTO> obtenerEntrevistasPorSede(String lugarSede) {
        return entrevistaRepository.findByLugarSede(lugarSede)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntrevistaHisDTO> obtenerEntrevistasPorCliente(String rucCliente) {
        return entrevistaRepository.findByClienteRuc(rucCliente)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
}
