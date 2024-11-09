package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Producto;
import com.megalabsapi.service.CatalogoService;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Producto> obtenerProductosEnCatalogo() {
        String jpql = "SELECT p FROM Producto p";
        TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class);
        return query.getResultList();
    }
}
