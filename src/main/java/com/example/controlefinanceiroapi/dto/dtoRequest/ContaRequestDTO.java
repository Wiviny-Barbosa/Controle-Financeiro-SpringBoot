package com.example.controlefinanceiroapi.dto.dtoRequest;

import java.util.UUID;

public record ContaRequestDTO(String nome, double saldo, UUID usuarioId) {
}
