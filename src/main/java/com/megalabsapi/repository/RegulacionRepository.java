package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Regulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulacionRepository extends JpaRepository<Regulacion, Integer> {
}