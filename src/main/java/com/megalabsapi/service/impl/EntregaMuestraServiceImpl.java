package com.megalabsapi.service.impl;

import com.megalabsapi.dto.EntregaMuestraDTO;
import com.megalabsapi.entity.Entrega_Muestra;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.service.EntregaMuestraService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EntregaMuestraServiceImpl implements EntregaMuestraService {
    //Debeen llevar la anotcion sprimframework, implementa la inyeccion de dependencias mediante la gestion de objetos
    //Elimina las instancias que no se usan, para spring detecte el contexto, debe usarse las anotacion
    //Depende de 1 o más repositorios
    private final EntregaMuestraRepository entregaMuestraRepository;


    @Override
    public List<EntregaMuestraDTO> obtenerEntregasPorCliente(String ruc) {
        List<Entrega_Muestra> entregas = entregaMuestraRepository.findByClienteRuc(ruc);

        // Convertir la lista de Entrega_Muestra a EntregaMuestraDTO
        return entregas.stream()
                .map(entrega -> new EntregaMuestraDTO(
                        entrega.getId(),
                        entrega.getLugar(),
                        entrega.getFecha(),
                        entrega.getEstado().name() // Usamos name() para obtener el String del Enum
                ))
                .collect(Collectors.toList());
    }





}
