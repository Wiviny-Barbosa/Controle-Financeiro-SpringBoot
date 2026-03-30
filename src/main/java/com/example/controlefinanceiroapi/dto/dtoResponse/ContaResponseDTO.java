package com.example.controlefinanceiroapi.dto.dtoResponse;

import com.example.controlefinanceiroapi.model.Usuario;

import java.util.UUID;

public record ContaResponseDTO(UUID id, String nome, Double saldo, Usuario usuario) {
}
