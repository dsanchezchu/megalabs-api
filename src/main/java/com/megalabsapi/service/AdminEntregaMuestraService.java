package com.megalabsapi.service;

import com.megalabsapi.model.entity.Entrega_Muestra;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminEntregaMuestraService {
    //Detallar los metodos que se haran en el sericio
    List<Entrega_Muestra> getAll();
    Page<Entrega_Muestra> paginate(Pageable pageable);
    Entrega_Muestra findById(Integer id);
    Entrega_Muestra create(Entrega_Muestra entregaMuestra);
    Entrega_Muestra update(Integer id,Entrega_Muestra updateEntregaMuestra);
    void delete(Integer id);

}
