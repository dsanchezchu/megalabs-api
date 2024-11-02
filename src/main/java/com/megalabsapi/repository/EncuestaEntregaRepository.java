package com.megalabsapi.repository;
import com.megalabsapi.model.entity.EncuestaEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaEntregaRepository extends JpaRepository<EncuestaEntrega, Long> {
}