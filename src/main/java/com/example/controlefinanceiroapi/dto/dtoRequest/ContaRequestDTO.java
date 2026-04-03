package com.example.controlefinanceiroapi.dto.dtoRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record ContaRequestDTO(@NotBlank(message = "Nome é obrigatório") String nome,
                              @NotNull(message = "Saldo é obrigatório")  @PositiveOrZero(message = "O saldo não pode ser negativo") Double saldo,
                              @NotNull(message = "O id do usuario é obrigatório") UUID usuarioId) {
}
