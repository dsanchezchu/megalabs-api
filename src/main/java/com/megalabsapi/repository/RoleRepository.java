package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Role;
import com.megalabsapi.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    // Método para buscar un rol por su nombre (usando el enum)
    Optional<Role> findByName(ERole name);
}

