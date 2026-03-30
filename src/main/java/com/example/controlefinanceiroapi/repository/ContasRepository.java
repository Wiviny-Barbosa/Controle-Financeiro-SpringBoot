package com.example.controlefinanceiroapi.repository;

import com.example.controlefinanceiroapi.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContasRepository extends JpaRepository<Conta, UUID> {
}
