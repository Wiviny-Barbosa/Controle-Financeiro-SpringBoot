package com.example.controlefinanceiroapi.repository;

import com.example.controlefinanceiroapi.model.Categoria;
import com.example.controlefinanceiroapi.model.Conta;
import com.example.controlefinanceiroapi.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacao, UUID> {

}
