package com.megalabsapi.service.impl;

import com.megalabsapi.dto.EntregaMuestraDTO;
import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.enums.EntregaStatus;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.service.EntregaMuestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.megalabsapi.service.AdminEntregaMuestraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
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
