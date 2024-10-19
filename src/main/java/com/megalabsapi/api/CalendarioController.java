package com.megalabsapi.api;

import com.megalabsapi.dto.EntrevistaDTO;
import com.megalabsapi.entity.Entrevista;
import com.megalabsapi.repository.EntrevistaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CalendarioController {

    @Autowired
    private EntrevistaRepository entrevistaRepository;

    // Endpoint para obtener las citas del representante, ahora devolviendo DTOs
    @GetMapping("/calendario/{dniRepresentante}")
    public List<EntrevistaDTO> obtenerCalendario(@PathVariable String dniRepresentante) {
        List<Entrevista> entrevistas = entrevistaRepository.findByRepresentanteDni(dniRepresentante);

        // Convertimos las entidades Entrevista a EntrevistaDTO
        return entrevistas.stream().map(entrevista ->
                new EntrevistaDTO(
                        entrevista.getIdEntrevista(),
                        entrevista.getFecha(),
                        entrevista.getHora(),
                        entrevista.getLugarSede(),
                        entrevista.getCliente().getNombre(),  // Obtenemos el nombre del cliente
                        entrevista.getRepresentante().getNombre() // Obtenemos el nombre del representante
                )
        ).collect(Collectors.toList());
    }
}
