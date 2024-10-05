package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.enums.EntregaStatus;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.service.AdminEntregaMuestraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EntregaMuestraServiceImpl implements AdminEntregaMuestraService {
    //Debeen llevar la anotcion sprimframework, implementa la inyeccion de dependencias mediante la gestion de objetos
    //Elimina las instancias que no se usan, para spring detecte el contexto, debe usarse las anotacion
    //Depende de 1 o más repositorios
    private final EntregaMuestraRepository entregaMuestraRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Entrega_Muestra> getAll() {
        //Metodos propipos de repositorio
        return entregaMuestraRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Entrega_Muestra> paginate(Pageable pageable) {
        return entregaMuestraRepository.findAll(pageable);
    }

    @Override
    public List<Entrega_Muestra> findByFecha(Date fecha) {
        return entregaMuestraRepository.findByFecha(fecha);
    }



    @Transactional
    @Override
    public Entrega_Muestra create(Entrega_Muestra entregaMuestra) {
        entregaMuestra.setCreatedAt(LocalDateTime.now());
        return entregaMuestraRepository.save(entregaMuestra);
    }

    @Transactional(readOnly = true)
    @Override
    public Entrega_Muestra findById(Integer id) {
        return entregaMuestraRepository.findById(id).orElseThrow(()-> new RuntimeException("Entrega-Muestra no funciona"));
    }

    @Transactional
    @Override
    public Entrega_Muestra update(Integer id, Entrega_Muestra updateEntregaMuestra) {
        Entrega_Muestra entregaMuestraFromDB = findById(id);
        entregaMuestraFromDB.setUpdatedAt(LocalDateTime.now());
        entregaMuestraFromDB.setLugar(updateEntregaMuestra.getLugar());
        entregaMuestraFromDB.setFecha(updateEntregaMuestra.getFecha());
        entregaMuestraFromDB.setEstado(updateEntregaMuestra.getEstado());
        entregaMuestraFromDB.setProducto(updateEntregaMuestra.getProducto());
        entregaMuestraFromDB.setCliente(updateEntregaMuestra.getCliente());
        return entregaMuestraRepository.save(entregaMuestraFromDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Entrega_Muestra entregaMuestra = findById(id);
        entregaMuestraRepository.delete(entregaMuestra);
    }

    @Override
    public List<Entrega_Muestra> findByClienteRuc(String RUC) {
        return entregaMuestraRepository.findByClienteRuc(RUC);
    }
}
