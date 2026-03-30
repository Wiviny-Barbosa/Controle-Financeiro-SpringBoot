package com.example.controlefinanceiroapi.repository;

import com.example.controlefinanceiroapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, UUID> {
}
