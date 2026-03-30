package com.example.controlefinanceiroapi.dto.dtoRequest;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(@NotBlank(message = "Nome é obrigatório") String nome) {
}
