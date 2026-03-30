package com.example.controlefinanceiroapi.repository;

import com.example.controlefinanceiroapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByEmail(String email);
}
