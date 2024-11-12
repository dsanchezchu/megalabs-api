package com.megalabsapi.repository;
import com.megalabsapi.model.entity.EncuestaRecojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRecojoRepository extends JpaRepository<EncuestaRecojo, Long> {
}