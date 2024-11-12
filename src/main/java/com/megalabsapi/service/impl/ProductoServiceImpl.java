package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Producto;
import com.megalabsapi.model.enums.StockStatus;
import com.megalabsapi.service.ProductoService;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Producto> obtenerProductosEnDesabastecimiento() {
        String jpql = "SELECT p FROM Producto p WHERE p.stock = :estado";
        TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class);
        query.setParameter("estado", StockStatus.AGOTADO);
        return query.getResultList();
    }
}

